package com.example.tonwalletapp.data.ton_remote.ton_config

import org.ton.api.liteclient.config.LiteClientConfigGlobal

interface TonLiteClientConfigFactory {

    suspend fun initLiteClientConfig()
    fun getLiteClientConfig():LiteClientConfigGlobal

}