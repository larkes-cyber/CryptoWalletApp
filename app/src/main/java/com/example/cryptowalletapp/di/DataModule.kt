package com.example.cryptowalletapp.di

import android.content.Context
import androidx.room.Room
import com.example.cryptowalletapp.data.database.AppDatabase
import com.example.cryptowalletapp.data.database.dao.UserDataDao
import com.example.cryptowalletapp.data.database.repository.DatabaseRepository
import com.example.cryptowalletapp.data.repositoty.AuthRepositoryData
import com.example.cryptowalletapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,"crypto_app_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDataDao(appDatabase: AppDatabase):UserDataDao{
        return appDatabase.userDataDao()
    }

    @Singleton
    @Provides
    fun provideDatabaseRepository(context: Context):DatabaseRepository{
        return DatabaseRepository(context)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
         databaseRepository: DatabaseRepository
    ):AuthRepository{
        return AuthRepositoryData(
            databaseRepository = databaseRepository
        )
    }

}