package com.example.tonwalletapp.di.data

import com.example.tonwalletapp.data.repository.DataRemoteRepository
import com.example.tonwalletapp.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataRemoteModule {

    @Singleton
    @Provides
    fun provideRemoteRepository():RemoteRepository{
        return DataRemoteRepository()
    }

}