package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGenerateSecretWords
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WalletUseCaseModule {

    @Provides
    fun provideUseGenerateSecretWords(
        walletRepository:WalletRepository
    ):UseGenerateSecretWords{
        return UseGenerateSecretWords(walletRepository = walletRepository)
    }

}