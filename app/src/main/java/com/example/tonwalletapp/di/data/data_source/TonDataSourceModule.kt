package com.example.tonwalletapp.di.data.data_source

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import com.example.tonwalletapp.data.remote.ton.TonLiteClientConfig
import com.example.tonwalletapp.data.remote.ton.TonLiteClientImpl
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
        tonLiteClient: TonLiteClient
    ): WalletTonDataSource = WalletTonDataSourceImpl(
        tonLiteClient = tonLiteClient
    )

    @Provides
    fun provideUserTonDataSource(
        tonLiteClientConfig: TonLiteClientConfig
    ):UserTonDataSource{
        return UserTonDataSourceImpl(tonLiteClientConfig = tonLiteClientConfig)
    }

}