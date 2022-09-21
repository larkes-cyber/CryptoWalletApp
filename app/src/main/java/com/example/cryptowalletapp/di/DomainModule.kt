package com.example.cryptowalletapp.di

import com.example.cryptowalletapp.domain.repository.AuthRepository
import com.example.cryptowalletapp.domain.usecase.UseGetUserData
import com.example.cryptowalletapp.domain.usecase.UseInsertUserData
import dagger.Module

@Module
class DomainModule {

    fun provideUseGetUserData(authRepository: AuthRepository):UseGetUserData{
        return UseGetUserData(authRepository)
    }
    fun provideUseSaveUserData(authRepository: AuthRepository):UseInsertUserData{
        return UseInsertUserData(authRepository)
    }

}