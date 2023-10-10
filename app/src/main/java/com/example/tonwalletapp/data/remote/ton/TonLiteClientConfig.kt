package com.example.tonwalletapp.data.remote.ton

import android.util.Log
import com.example.tonwalletapp.until.Constants.TON_GLOBAL_CONFIG_URL
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import java.net.URL
import kotlin.coroutines.CoroutineContext

class TonLiteClientConfig(
    private val httpClient: HttpClient
) {

    private var tonLiteClientConfig: LiteClientConfigGlobal? = null

    suspend fun setupConfig() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = URL(TON_GLOBAL_CONFIG_URL).readText()
            Log.d("dfdsfsdfsdfsdf", response.toString())
        }
    }

    suspend  fun getConfig():LiteClientConfigGlobal{
        if(tonLiteClientConfig == null){
            setupConfig()
        }
        return tonLiteClientConfig!!
    }

}