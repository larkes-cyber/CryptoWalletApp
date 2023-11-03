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
    fun provideUseCreateWallet(walletRepository:WalletRepository):UseCreateWallet{
        return UseCreateWallet(
            walletRepository = walletRepository
        )
    }
    @Provides
    fun provideUseGetFirstWalletWords(walletRepository: WalletRepository):UseGetFirstWalletWords{
        return UseGetFirstWalletWords(walletRepository)
    }
    @Provides
    fun provideUseGetWalletInfo(walletRepository: WalletRepository):UseGetWalletInfo{
        return UseGetWalletInfo(walletRepository)
    }
    @Provides
    fun provideUseImportWallet(walletRepository: WalletRepository):UseImportWallet{
        return UseImportWallet(walletRepository)
    }

    @Provides
    fun provideUseGetWallets(walletRepository: WalletRepository):UseGetWallets{
        return UseGetWallets(walletRepository)
    }

    @Provides
    fun provideUseGetTransactionsByAddress(walletRepository: WalletRepository):UseGetTransactionsByAddress{
        return UseGetTransactionsByAddress(walletRepository)
    }

    @Provides
    fun provideUseGetTransactionFee(walletRepository: WalletRepository):UseGetTransactionFee{
        return UseGetTransactionFee(walletRepository)
    }

    @Provides
    fun provideUseMakeTransaction(walletRepository: WalletRepository):UseMakeTransaction{
        return UseMakeTransaction(
            walletRepository = walletRepository
        )
    }
}
