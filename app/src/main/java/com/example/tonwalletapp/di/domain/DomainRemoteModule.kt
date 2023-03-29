package com.example.tonwalletapp.di.domain

import com.example.tonwalletapp.domain.repository.RemoteRepository
import com.example.tonwalletapp.domain.usecase.UseGetSecretWords
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainRemoteModule {

    @Provides
    fun provideUseGetSecretWords(
        remoteRepository: RemoteRepository
    ):UseGetSecretWords{
        return UseGetSecretWords(remoteRepository = remoteRepository)
    }

}