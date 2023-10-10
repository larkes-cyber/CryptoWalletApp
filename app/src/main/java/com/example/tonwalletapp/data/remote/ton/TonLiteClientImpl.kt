package com.example.tonwalletapp.data.remote.ton

import android.util.Log
import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.until.Constants.TON_GLOBAL_CONFIG_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.MsgAddressExt
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.lite.client.LiteClient
import org.ton.mnemonic.Mnemonic
import java.net.URL

class TonLiteClientImpl(
    private val coroutineScope: CoroutineScope,
    private val tonLiteClientConfig: TonLiteClientConfig

):TonLiteClient {

    private var liteClient:LiteClient? = null

    suspend fun initLiteClient(){
        liteClient = LiteClient(coroutineScope.coroutineContext, tonLiteClientConfig.getConfig())
        Log.d("dfsdfsdfsdf","##################")
    }


    override suspend fun getWalletInfo(words: List<String>): WalletTon {

        val keyPair = Mnemonic.toSeed(mnemonic = words)

        val privateKey = PrivateKeyEd25519.of(keyPair)
        val publicKey = PublicKeyEd25519.of(privateKey)

        val walletContract = WalletV4R2Contract(0,  publicKey)

        return WalletTon(
            privateKey = privateKey,
            publicKey = publicKey,
            address = MsgAddressInt.toString(walletContract.address),
            words = words
        )
    }

    override suspend fun getWalletBalance(address: String): Float {
        initLiteClient()
        initLiteClient()
        val account = liteClient!!.getAccount("EQCZDt4LYNyknIoYnrhsK5Ka2fHdC1BP2YYO9ig8U7oTdlEK")
        Log.d("sdfsdfsdfsdfsdf",account.toString())
        return account!!.storage.balance.coins.amount.toFloat()
    }

    override suspend fun getTransactionsList(address: String): List<TransactionDetailTon> {
        return listOf()
    }


}