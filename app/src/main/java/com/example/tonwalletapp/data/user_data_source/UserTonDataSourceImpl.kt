package com.example.tonwalletapp.data.user_data_source

import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory


class UserTonDataSourceImpl(
   private val tonLiteClientFactory: TonLiteClientFactory
):UserTonDataSource {
    override suspend fun initializeTonLiteClient() {
        tonLiteClientFactory.initLiteClient()
    }

}