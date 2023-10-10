package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.domain.usecase.wallet_usecase.*
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
    fun provideUseSetupWalletWallet(
        walletRepository: WalletRepository
    ):UseSetupWallet{
        return UseSetupWallet(walletRepository = walletRepository)
    }


    @Provides
    fun provideUseGetWallets(
        walletRepository: WalletRepository
    ):UseGetAllWallets{
        return UseGetAllWallets(walletRepository)
    }

    @Provides
    fun provideUseGetAllWalletsId(
        walletRepository: WalletRepository
    ):UseGetAllWalletsId{
        return UseGetAllWalletsId(walletRepository = walletRepository)
    }

    @Provides
    fun provideUseGetWalletDetailInfo(
        walletRepository: WalletRepository
    ):UseGetWalletDetailInfo{
        return UseGetWalletDetailInfo(walletRepository = walletRepository)
    }

}