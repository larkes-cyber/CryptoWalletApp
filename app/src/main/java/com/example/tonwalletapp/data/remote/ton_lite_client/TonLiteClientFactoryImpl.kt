package com.example.tonwalletapp.data.remote.ton_lite_client

import com.example.tonwalletapp.until.Constants.TON_GLOBAL_CONFIG_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.lite.client.LiteClient

class TonLiteClientFactoryImpl(
    private val httpClient: HttpClient
):TonLiteClientFactory {

    private var liteClientConfig:LiteClientConfigGlobal? = null

    override suspend fun initLiteClientConfig(){
        val job = CoroutineScope(Dispatchers.IO).launch {
            val response = httpClient.get(TON_GLOBAL_CONFIG_URL).bodyAsText()
            liteClientConfig = Json{ ignoreUnknownKeys = true }.decodeFromString<LiteClientConfigGlobal>(response)
        }
        job.join()
    }

    override fun getLiteClientConfig():LiteClientConfigGlobal{
        return liteClientConfig!!
    }

}