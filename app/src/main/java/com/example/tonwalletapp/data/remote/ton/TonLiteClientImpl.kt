package com.example.tonwalletapp.data.remote.ton

import android.util.Log
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.api.liteserver.LiteServerDesc
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.contract.wallet.v4.ContractV4R2
import org.ton.crypto.base64
import org.ton.lite.client.LiteClient
import org.ton.mnemonic.Mnemonic

class TonLiteClientImpl(
    private val tonLiteClientFactory: TonLiteClientFactory
):TonLiteClient {


    override suspend fun getWalletInfo(words: List<String>): WalletTon {

        val keyPair = Mnemonic.toSeed(mnemonic = words.toTypedArray())

        val privateKey = PrivateKeyEd25519.of(keyPair)
        val publicKey = PublicKeyEd25519.of(privateKey)

        var address:String? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            val tonLiteClient = LiteClient(this.coroutineContext, liteClientConfigGlobal = tonLiteClientFactory.getLiteClient())
            val walletContract = ContractV4R2(tonLiteClient,  privateKey)
            walletContract.createStateInit()
            walletContract.createDataInit()
            walletContract.createExternalInitMessage()
            walletContract.deploy()
            address = walletContract.address().toString(userFriendly = true)
        }
        job.join()
        job.invokeOnCompletion {

        }
        return WalletTon(
            privateKey = privateKey,
            publicKey = publicKey,
            address = address!!,
            words = words
        )
    }

    override suspend fun getWalletBalance(address: String): Float {

        var accountBalance:Float? = null

        val job = CoroutineScope(Dispatchers.IO).launch {
            Log.d("sadsdfsdfsdfddd", address)

            val tonLiteClient = LiteClient(this.coroutineContext, liteClientConfigGlobal = LiteClientConfigGlobal(
                liteServers = listOf(
                    LiteServerDesc(id = PublicKeyEd25519(base64("n4VDnSCUuSpjnCyUk9e3QOOd6o0ItSWYbTnW3Wnn8wk=")), ip = 84478511, port = 19949)
                )
            ))

            val accountInfo = tonLiteClient.getAccount(address)


            Log.d("dsfdfddff",accountInfo.toString())
            accountBalance = 12f
        }//accountInfo.balance.coins.toString().toFloat()

        job.join()

        return accountBalance!!
    }

    override suspend fun getTransactionsList(address: String): List<TransactionDetailTon> {
        return listOf()
    }


}