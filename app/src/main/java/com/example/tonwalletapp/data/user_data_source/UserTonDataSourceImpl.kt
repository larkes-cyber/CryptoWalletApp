package com.example.tonwalletapp.data.user_data_source

import com.example.tonwalletapp.data.remote.ton.TonLiteClientConfig

class UserTonDataSourceImpl(
    private val tonLiteClientConfig: TonLiteClientConfig
):UserTonDataSource {
    override suspend fun setupTonConfig() {
        tonLiteClientConfig.setupConfig()
    }
}