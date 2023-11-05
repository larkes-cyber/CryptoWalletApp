package com.example.tonwalletapp.di.data.data_source

import com.example.tonwalletapp.data.ton_remote.ton_client.TonClient
import com.example.tonwalletapp.data.ton_remote.ton_config.TonLiteClientConfigFactory
import com.example.tonwalletapp.data.user_data_source.UserTonDataSource
import com.example.tonwalletapp.data.user_data_source.UserTonDataSourceImpl
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TonDataSourceModule {
    @Singleton
    @Provides
    fun provideWalletTonDataSource(
        tonClient: TonClient
    ): WalletTonDataSource = WalletTonDataSourceImpl(
        tonClient = tonClient
    )

    @Provides
    fun provideUserTonDataSource(
        tonLiteClientConfigFactory: TonLiteClientConfigFactory
    ):UserTonDataSource{
        return UserTonDataSourceImpl(tonLiteClientConfigFactory = tonLiteClientConfigFactory)
    }

}