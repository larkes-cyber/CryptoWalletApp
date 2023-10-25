package com.example.tonwalletapp.data.remote.ton_lite_client

import org.ton.lite.client.LiteClient

interface TonLiteClientFactory {

    suspend fun initLiteClient()
    fun getLiteClient():LiteClient

}