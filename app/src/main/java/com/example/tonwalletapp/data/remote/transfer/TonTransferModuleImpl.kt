package com.example.tonwalletapp.data.remote.transfer

import android.util.Log
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
import com.example.tonwalletapp.data.remote.wallet.TonWalletModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.bitstring.BitString
import org.ton.block.AddrNone
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.block.CommonMsgInfoRelaxed
import org.ton.block.Either
import org.ton.block.ExtInMsgInfo
import org.ton.block.Maybe
import org.ton.block.Message
import org.ton.block.MessageRelaxed
import org.ton.block.MsgAddressInt
import org.ton.block.StateInit
import org.ton.boc.BagOfCells
import org.ton.cell.Cell
import org.ton.cell.CellBuilder
import org.ton.cell.buildCell
import org.ton.contract.wallet.WalletContract
import org.ton.contract.wallet.WalletTransfer
import org.ton.lite.api.liteserver.functions.LiteServerSendMessage
import org.ton.lite.client.LiteClient
import org.ton.tlb.CellRef
import org.ton.tlb.constructor.AnyTlbConstructor
import org.ton.tlb.storeRef
import org.ton.tlb.storeTlb

class TonTransferModuleImpl(
    private val tonWalletModule: TonWalletModule,
    private val tonStateModule: TonStateModule,
    private val liteClientFactory: TonLiteClientFactory
):TonTransferModule {

    private val liteClientConfig = liteClientFactory.getLiteClientConfig()

    override suspend fun makeTransfer(walletTon: WalletTon, amount: Double, address: String) {

        val checkWalletInit = tonWalletModule.checkWalletInitialization(walletTon.address)
        createTransfer(
            stateInit = if(checkWalletInit) null else tonStateModule.createStateInit(walletTon.privateKey),
            privateKey = walletTon.privateKey,
            seqno = if(!checkWalletInit) 0 else tonWalletModule.getSeqno(walletTon.address)!!,
            address = AddrStd(walletTon.address),
            transfers = listOf(Pair(address, amount.toNano())).toWalletTransfer().toTypedArray()
        )
    }

    private suspend fun createTransfer(
        stateInit: StateInit?,
        subwalletId:Int = WalletContract.DEFAULT_WALLET_ID + 0,
        privateKey:PrivateKeyEd25519,
        address: AddrStd,
        seqno: Int,
        vararg transfers: WalletTransfer

    ) {
        val message = createTransferMessage(
            address = address,
            stateInit = stateInit,
            privateKey = privateKey,
            validUntil = Int.MAX_VALUE,
            walletId = subwalletId,
            seqno = seqno,
            transfers = transfers
        )
        sendExternalMessage(buildCell { storeTlb(Message.tlbCodec(AnyTlbConstructor), message) })
    }


    private suspend fun sendExternalMessage(message: Cell): Int {

        var res:Int? = null

        val job = CoroutineScope(Dispatchers.IO).launch{
            val liteClient = LiteClient(this.coroutineContext, liteClientConfig)
            res = liteClient.liteApi(LiteServerSendMessage(BagOfCells(message).toByteArray())).status
        }
        job.join()
        return res!!

    }


    private fun createTransferMessage(
        address: MsgAddressInt,
        stateInit: StateInit?,
        privateKey: PrivateKeyEd25519,
        walletId: Int,
        validUntil: Int,
        seqno: Int,
        vararg transfers: WalletTransfer
    ): Message<Cell> {
        val info = ExtInMsgInfo(
            src = AddrNone,
            dest = address,
            importFee = Coins()
        )
        val maybeStateInit =
            Maybe.of(stateInit?.let { Either.of<StateInit, CellRef<StateInit>>(null, CellRef(it)) })
        val transferBody = createTransferMessageBody(
            privateKey,
            walletId,
            validUntil,
            seqno,
            *transfers
        )
        val body = Either.of<Cell, CellRef<Cell>>(null, CellRef(transferBody))
        return Message(
            info = info,
            init = maybeStateInit,
            body = body
        )
    }



    private fun createTransferMessageBody(
        privateKey: PrivateKeyEd25519,
        walletId: Int,
        validUntil: Int,
        seqno: Int,
        vararg gifts: WalletTransfer
     ): Cell {
        val unsignedBody = CellBuilder.createCell {
            storeUInt(walletId, 32)
            storeUInt(validUntil, 32)
            storeUInt(seqno, 32)
            storeUInt(0, 8) // OP_TRANSFER
            for (gift in gifts) {
                var sendMode = 3
                if (gift.sendMode > -1) {
                    sendMode = gift.sendMode
                }
                val intMsg = CellRef(createIntMsg(gift,privateKey))

                storeUInt(sendMode, 8)
                storeRef(MessageRelaxed.tlbCodec(AnyTlbConstructor), intMsg)
            }
        }
        val signature = BitString(privateKey.sign(unsignedBody.hash().toByteArray()))

        return CellBuilder.createCell {
            storeBits(signature)
            storeBits(unsignedBody.bits)
            storeRefs(unsignedBody.refs)
        }
    }


    private fun createIntMsg(gift: WalletTransfer, privateKey: PrivateKeyEd25519): MessageRelaxed<Cell> {
        val info = CommonMsgInfoRelaxed.IntMsgInfoRelaxed(
            ihrDisabled = true,
            bounce = gift.bounceable,
            bounced = false,
            src = AddrNone,
            dest = gift.destination,
            value = gift.coins,
            ihrFee = Coins(),
            fwdFee = Coins(),
            createdLt = 0u,
            createdAt = 0u
        )
        val init = Maybe.of(gift.stateInit?.let {
            Either.of<StateInit, CellRef<StateInit>>(tonStateModule.createStateInit(privateKey), null)
        })


        return MessageRelaxed(
            info = info,
            init = init,
            body = Either.of<Cell, CellRef<Cell>>(Cell.empty(), null),
        )
    }
}

fun List<Pair<String, Long>>.toWalletTransfer() = this.map {
    WalletTransfer {
        destination = AddrStd(it.first)
        coins = Coins.ofNano(it.second)
        bounceable = true
        sendMode = 1
    }
}

const val NANOCOIN: Long = 1_000_000_000

fun Double.toNano(): Long = (this * NANOCOIN).toLong()