package com.example.tonwalletapp.di.data

import com.example.tonwalletapp.data.remote.repository.DataRemoteRepository
import com.example.tonwalletapp.domain.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataRemoteModule {

    @Provides
    fun provideRemoteRepository():RemoteRepository{
        return DataRemoteRepository()
    }

}