package com.example.tonwalletapp.di.data

import android.content.Context
import com.example.tonwalletapp.data.local.shared.SharedStorage
import com.example.tonwalletapp.data.repository.DataLocalRepository
import com.example.tonwalletapp.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataLocalModule {

    @Singleton
    @Provides
    fun provideSharedStorage(context: Context):SharedStorage{
        return SharedStorage(context)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(sharedStorage: SharedStorage):LocalRepository{
        return DataLocalRepository(sharedStorage)
    }

}