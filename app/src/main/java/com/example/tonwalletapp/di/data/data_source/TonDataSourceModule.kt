package com.example.tonwalletapp.di.data.data_source

import com.example.tonwalletapp.data.remote.TonClient
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
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
        tonLiteClientFactory: TonLiteClientFactory
    ):UserTonDataSource{
        return UserTonDataSourceImpl(tonLiteClientFactory = tonLiteClientFactory)
    }

}