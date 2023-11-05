package com.example.tonwalletapp.data.remote.wallet

import android.util.Log
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
import com.example.tonwalletapp.domain.mapper.mapTx
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.NANO_NUM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
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
import org.ton.lite.client.LiteClient
import java.net.URL
import kotlin.coroutines.suspendCoroutine

class TonWalletModuleImpl(
    private val tonStateModule: TonStateModule,
    private val tonLiteClientFactory: TonLiteClientFactory
):TonWalletModule {

    private val liteClientConfig = tonLiteClientFactory.getLiteClientConfig()

    override suspend fun getSeqno(address: String):Int? {
        val stack = runGetMethod("seqno", address = AddrStd(address))?.stack
        return stack?.toMutableVmStack()?.popInt()?.toInt()
    }

    override suspend fun getWalletAddress(publicKeyEd25519: PublicKeyEd25519): String {
        val contract = WalletV4R2Contract(0, publicKeyEd25519)
        return MsgAddressInt.toString(contract.address, userFriendly = true)
    }


    override suspend fun getTransactionList(address: String): List<TransactionDetailTon>? {
        val account = tonStateModule.getAccountState(address)
        var txt:List<TransactionDetailTon>? = null
        if(account != null) {
            val job = CoroutineScope(Dispatchers.IO).launch {
                val liteClient = LiteClient(this.coroutineContext, liteClientConfig)
                txt = liteClient.getTransactions(
                    accountAddress = AddrStd(address),
                    fromTransactionId = account.lastTransactionId!!,
                    count = 10
                ).map {
                    mapTx(it.transaction.value, it.blockId.seqno, it.blockId.workchain)
                }
            }
            job.join()
        }
        return txt
    }

    override suspend fun getWalletBalance(address: String): Float? {
        val accountState = tonStateModule.getAccountState(address) ?: return null

        val info = accountState.account.value as AccountInfo
        val balance = info.storage.balance.coins.amount.value.toFloat()

        return balance / NANO_NUM

    }

    override suspend fun checkWalletInitialization(address: String): Boolean {
        return getSeqno(address) != null
    }


    private suspend fun runGetMethod(method: String, address: AddrStd): SmartContractAnswer? {

        var smartContractAnswer:SmartContractAnswer? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val liteClient = LiteClient(this.coroutineContext, liteClientConfig)
                val lastBlockId = liteClient.liteApi(LiteServerGetMasterchainInfo).last
                val result = liteClient.liteApi(
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


                smartContractAnswer = SmartContractAnswer(
                    stack = vmStack,
                    exitCode = result.exitCode
                )
            }catch (e:Exception){
                smartContractAnswer = null
            }
        }

        job.join()
        return smartContractAnswer

    }

}