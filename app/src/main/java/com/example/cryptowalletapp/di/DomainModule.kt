package com.example.cryptowalletapp.di

import com.example.cryptowalletapp.domain.repository.AuthRepository
import com.example.cryptowalletapp.domain.repository.CoinRepository
import com.example.cryptowalletapp.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideUseGetUserData(authRepository: AuthRepository):UseGetUserData{
        return UseGetUserData(authRepository)
    }

    @Provides
    fun provideUseSaveUserData(authRepository: AuthRepository):UseInsertUserData{
        return UseInsertUserData(authRepository)
    }

    @Provides
    fun provideUseCheckAlreadyUser(authRepository: AuthRepository):UseCheckAlreadyUser{
        return UseCheckAlreadyUser(authRepository)
    }
    @Provides
    fun provideUseGetSmallCoinInfo(coinRepository: CoinRepository):UseGetSmallCoinInfo{
        return UseGetSmallCoinInfo(coinRepository)
    }

    @Provides
    fun provideUseGetCurrencyLogoBySyb(coinRepository: CoinRepository):UseGetCurrencyLogoBySym{
        return UseGetCurrencyLogoBySym(coinRepository)
    }

}