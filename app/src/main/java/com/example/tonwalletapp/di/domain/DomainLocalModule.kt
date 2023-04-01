package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.LocalRepository
import com.example.tonwalletapp.domain.usecase.UseGetLocalSecretWords
import com.example.tonwalletapp.domain.usecase.UseSaveLocalSecretWords
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLocalModule {

    @Provides
    fun provideUseSaveLocalSecretWords(
        localRepository: LocalRepository
    ):UseSaveLocalSecretWords{
        return UseSaveLocalSecretWords(localRepository = localRepository)
    }

    @Provides
    fun provideUseGetLocalSecretWords(
        localRepository: LocalRepository
    ):UseGetLocalSecretWords{
        return UseGetLocalSecretWords(localRepository)
    }

}