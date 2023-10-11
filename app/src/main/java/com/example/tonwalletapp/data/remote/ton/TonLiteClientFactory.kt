package com.example.tonwalletapp.data.remote.ton

import com.example.tonwalletapp.until.Constants.TON_GLOBAL_CONFIG_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.lite.client.LiteClient
import kotlin.coroutines.CoroutineContext

class TonLiteClientFactory(
    private val httpClient: HttpClient
) {

    private var liteClient:LiteClient? = null
    private val coroutineContext = CoroutineScope(Dispatchers.IO).coroutineContext

    suspend fun initLiteClient(){
        val response = httpClient.get(TON_GLOBAL_CONFIG_URL).bodyAsText()
        val tonConfig = Json{ ignoreUnknownKeys = true }.decodeFromString<LiteClientConfigGlobal>(response)
        liteClient = LiteClient(coroutineContext = coroutineContext, tonConfig)
    }

    fun getLiteClient():LiteClient{
        return liteClient!!
    }

}