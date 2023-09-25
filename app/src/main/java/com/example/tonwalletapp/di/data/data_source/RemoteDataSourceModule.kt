package com.example.tonwalletapp.di.data.data_source

import com.example.tonwalletapp.data.wallet_data_source.WalletRemoteDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    fun provideWalletRemoteDataSource():WalletRemoteDataSource = WalletRemoteDataSourceImpl()

}