package com.example.tonwalletapp.di.data.repository

import com.example.tonwalletapp.data.repository.UserRepositoryImpl
import com.example.tonwalletapp.data.repository.WalletRepositoryImpl
import com.example.tonwalletapp.data.user_data_source.UserDiskDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletRemoteDataSource
import com.example.tonwalletapp.domain.repository.UserRepository
import com.example.tonwalletapp.domain.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        userDiskDataSource: UserDiskDataSource
    ):UserRepository = UserRepositoryImpl(
        userDiskDataSource = userDiskDataSource
    )

    @Provides
    fun provideWalletRepository(walletRemoteDataSource: WalletRemoteDataSource):WalletRepository{
        return WalletRepositoryImpl(walletRemoteDataSource = walletRemoteDataSource)
    }

}