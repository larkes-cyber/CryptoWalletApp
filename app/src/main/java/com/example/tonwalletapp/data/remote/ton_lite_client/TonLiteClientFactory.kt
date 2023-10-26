package com.example.tonwalletapp.data.remote.ton_lite_client

import org.ton.api.liteclient.config.LiteClientConfigGlobal

interface TonLiteClientFactory {

    suspend fun initLiteClientConfig()
    fun getLiteClientConfig():LiteClientConfigGlobal

}