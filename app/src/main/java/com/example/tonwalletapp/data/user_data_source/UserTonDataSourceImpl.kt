package com.example.tonwalletapp.data.user_data_source

import com.example.tonwalletapp.data.ton_remote.ton_config.TonLiteClientConfigFactory


class UserTonDataSourceImpl(
   private val tonLiteClientConfigFactory: TonLiteClientConfigFactory
):UserTonDataSource {
    override suspend fun initializeTonLiteClient() {
        tonLiteClientConfigFactory.initLiteClientConfig()
    }

}