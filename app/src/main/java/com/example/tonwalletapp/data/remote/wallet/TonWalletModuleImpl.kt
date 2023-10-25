package com.example.tonwalletapp.data.remote.wallet

import android.util.Log
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
import com.example.tonwalletapp.domain.mapper.mapTx
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.AccountInfo
import org.ton.block.AddrStd
import org.ton.block.MsgAddressInt
import org.ton.block.VmStack
import org.ton.boc.BagOfCells
import org.ton.contract.SmartContractAnswer
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.lite.api.liteserver.LiteServerAccountId
import org.ton.lite.api.liteserver.functions.LiteServerGetMasterchainInfo
import org.ton.lite.api.liteserver.functions.LiteServerRunSmcMethod
import org.ton.mnemonic.Mnemonic

class TonWalletModuleImpl(
    private val tonStateModule: TonStateModule,
    private val tonLiteClientFactory: TonLiteClientFactory
):TonWalletModule {

    private val liteClient = tonLiteClientFactory.getLiteClient()

    override suspend fun getSeqno(address: String):Int? {
        val stack = runGetMethod("seqno", address = AddrStd(address)).stack
        return stack?.toMutableVmStack()?.popInt()?.toInt()
    }

    override suspend fun getWalletAddress(publicKeyEd25519: PublicKeyEd25519): String {
        val contract = WalletV4R2Contract(0, publicKeyEd25519)
        return MsgAddressInt.toString(contract.address, userFriendly = true)
    }


    override suspend fun getTransactionList(address: String): List<TransactionDetailTon> {

        val account = tonStateModule.getAccountState(address)
        return liteClient.getTransactions(
            accountAddress =AddrStd(address),
            fromTransactionId = account.lastTransactionId!!,
            count = 10
        ).map {
            mapTx(it.transaction.value, it.blockId.seqno, it.blockId.workchain)
        }
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return try {
            val account = liteClient.getAccountState(AddrStd(address)).account.value
            val info = account as AccountInfo

            info.storage.balance.coins.amount.toFloat()
        }catch (e:Exception){
            Log.d("dsfsdfsdfsdffsd",e.message.toString())
            null
        }
    }


    private suspend fun runGetMethod(method: String, address: AddrStd): SmartContractAnswer {
        val lastBlockId = tonLiteClientFactory.getLiteClient().liteApi(LiteServerGetMasterchainInfo).last
        val result = tonLiteClientFactory.getLiteClient().liteApi(
            LiteServerRunSmcMethod(
                mode = 4,
                id = lastBlockId,
                account = LiteServerAccountId(address.workchainId, address.address),
                methodId = LiteServerRunSmcMethod.methodId(method),
                params = LiteServerRunSmcMethod.params()
            )
        )
        var vmStack: VmStack? = null
        vmStack = VmStack.loadTlb(BagOfCells(result.result!!).first())


        return SmartContractAnswer(
            stack = vmStack,
            exitCode = result.exitCode
        )
    }

}