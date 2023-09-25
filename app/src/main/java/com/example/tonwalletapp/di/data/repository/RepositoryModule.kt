package com.example.tonwalletapp.di.data.repository

import com.example.tonwalletapp.data.repository.UserRepositoryImpl
import com.example.tonwalletapp.data.user_data_source.UserDiskDataSource
import com.example.tonwalletapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        userDiskDataSource: UserDiskDataSource
    ):UserRepository = UserRepositoryImpl(
        userDiskDataSource = userDiskDataSource
    )

}