package com.example.tonwalletapp.data.remote.wallet

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.ton.TonLiteClientFactory
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.MsgAddressInt
import org.ton.block.VmStack
import org.ton.boc.BagOfCells
import org.ton.contract.SmartContractAnswer
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.lite.api.LiteApi
import org.ton.lite.api.liteserver.LiteServerAccountId
import org.ton.lite.api.liteserver.functions.LiteServerGetAccountState
import org.ton.lite.api.liteserver.functions.LiteServerGetBlock
import org.ton.lite.api.liteserver.functions.LiteServerGetMasterchainInfo
import org.ton.lite.api.liteserver.functions.LiteServerGetTransactions
import org.ton.lite.api.liteserver.functions.LiteServerLookupBlock
import org.ton.lite.api.liteserver.functions.LiteServerRunSmcMethod
import org.ton.mnemonic.Mnemonic

class TonWalletModuleImpl(
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

    override suspend fun createNewWallet(): WalletTon {

        val words = Mnemonic.generate()
        val wordsSeed = Mnemonic.toSeed(words)
        val privateKey = PrivateKeyEd25519.of(wordsSeed)
        val publicKey = PublicKeyEd25519.of(privateKey)
        val address = getWalletAddress(publicKey)

        return WalletTon(
            publicKey = publicKey,
            privateKey = privateKey,
            words = words,
            address = address
        )
    }

    override suspend fun getTransactionList(address: AddrStd, lt:Long, hash:ByteArray): List<TransactionDetailTon> {
        val liteClient = tonLiteClientFactory.getLiteClient()
        liteClient.liteApi.invoke(LiteServerGetTransactions(10, LiteServerAccountId(0, address.address),lt, hash ))
    }
//.lookupBlockByLt(accountState.shard_blk, getLastTransactionLt(address))
    private suspend fun getLastTransactionLtHash(address: AddrStd) : Pair<Long, ByteArray>? {
        val account = LiteServerAccountId(0, address.address)
        val lastBlockId = liteClient.getLastBlockId()
        val accountState = liteClient.liteApi.invoke(LiteServerGetAccountState(lastBlockId, account))
        val trBlockHeader = liteClient.liteApi.invoke(LiteServerLookupBlock(mode = 4, id = lastBlockId, lt = getLastTransactionLt(address.toString(userFriendly = true)), utime = 0))
        val trBlock = liteClient.liteApi(LiteServerGetBlock(trBlockHeader.id))
        val accountBlocks = trBlock.data.get()
        for (node in accountBlocks.nodes()) {
            if (node.first.account_addr == account.toMsgAddressIntStd().address) {
                val blocks = node.first.transactions
                for (tr in blocks.nodes()) {
                    return Pair(tr.first.lt.toLong(), tr.first.hash())
                }
            }
        }
        return null
    }

    private suspend fun getLastTransactionLt(address: String) : Long {
        val accountInfo = liteClient.getAccount(address)
        val lastTransTt = accountInfo?.storage?.lastTransLt
        return lastTransTt?.toLong() ?: 0
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