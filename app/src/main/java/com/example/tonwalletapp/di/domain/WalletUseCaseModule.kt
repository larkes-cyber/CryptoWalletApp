package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseCreateWallet
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetSecretWords
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WalletUseCaseModule {

    @Provides
    fun provideUseGetSecretWords(
        walletRepository:WalletRepository
    ):UseGetSecretWords{
        return UseGetSecretWords(walletRepository = walletRepository)
    }

    @Provides
    fun provideUseCreateWallet(
        walletRepository: WalletRepository
    ):UseCreateWallet{
        return UseCreateWallet(walletRepository = walletRepository)
    }

}