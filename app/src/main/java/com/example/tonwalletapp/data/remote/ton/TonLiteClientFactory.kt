package com.example.tonwalletapp.data.remote.ton

import android.util.Log
import com.example.tonwalletapp.until.Constants.TON_GLOBAL_CONFIG_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.lite.client.LiteClient
import kotlin.coroutines.CoroutineContext

class TonLiteClientFactory(
    private val httpClient: HttpClient
) {
    private var liteClientConfig:LiteClientConfigGlobal? = null

    suspend fun initLiteClient(){
        val response = httpClient.get(TON_GLOBAL_CONFIG_URL).bodyAsText()
        Log.d("dfsdfsdfsdfsdfsdf",response.toString())
        liteClientConfig = Json{ ignoreUnknownKeys = true }.decodeFromString<LiteClientConfigGlobal>(response)
    }

    fun getLiteClientConfig():LiteClientConfigGlobal{
        return liteClientConfig!!
    }

}