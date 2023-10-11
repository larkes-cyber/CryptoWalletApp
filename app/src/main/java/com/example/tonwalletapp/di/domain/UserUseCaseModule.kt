package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.UserRepository
import com.example.tonwalletapp.domain.usecase.user_usecase.UseCleanUpUserData
import com.example.tonwalletapp.domain.usecase.user_usecase.UseGetPassCode
import com.example.tonwalletapp.domain.usecase.user_usecase.UseInitializeTonLiteClient
import com.example.tonwalletapp.domain.usecase.user_usecase.UseSavePassCode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {

    @Provides
    fun provideUseSavePassCode(userRepository: UserRepository):UseSavePassCode{
        return UseSavePassCode(userRepository = userRepository)
    }

    @Provides
    fun provideUseGetPassCode(userRepository: UserRepository):UseGetPassCode{
        return UseGetPassCode(userRepository = userRepository)
    }

    @Provides
    fun provideUseCleanUpUserData(userRepository: UserRepository):UseCleanUpUserData{
        return UseCleanUpUserData(userRepository = userRepository)
    }

    @Provides
    fun provideUseInitializeTonLiteClient(userRepository: UserRepository):UseInitializeTonLiteClient{
        return UseInitializeTonLiteClient(userRepository = userRepository)
    }

}