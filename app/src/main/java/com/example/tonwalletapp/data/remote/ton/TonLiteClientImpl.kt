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
    private val tonLiteClientFactory: TonLiteClientFactory
):TonLiteClient {


    private val liteClient = tonLiteClientFactory.getLiteClient()

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

        val accountInfo = liteClient.getAccount("UQC1yUHwfKkbPXQKB0keNjlKog8-o_mtL9KS1J14d2R5JEzr")!!.storage
        Log.d("sdfsdfsdfsdfsdf",accountInfo.balance.coins.toString())
        return accountInfo.balance.coins.amount.toFloat()
    }

    override suspend fun getTransactionsList(address: String): List<TransactionDetailTon> {
        return listOf()
    }


}