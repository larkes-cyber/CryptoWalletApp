package com.example.tonwalletapp.data.remote.ton

import android.util.Log
import com.example.tonwalletapp.data.database.entity.WalletEntity
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
    private val coroutineScope: CoroutineScope
):TonLiteClient {

    private var liteClient:LiteClient? = null

    init {
        coroutineScope.launch {
            val liteConfig = Json{ ignoreUnknownKeys = true }.decodeFromString<LiteClientConfigGlobal>(URL(TON_GLOBAL_CONFIG_URL).readText())
            liteClient = LiteClient(coroutineContext = coroutineContext, liteConfig)
        }

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




}