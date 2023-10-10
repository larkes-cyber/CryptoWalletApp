package com.example.tonwalletapp.di.data.data_source

import android.content.Context
import androidx.room.Room
import com.example.tonwalletapp.data.database.AppDatabase
import com.example.tonwalletapp.data.database.dao.WalletDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,"wallet_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideWalletDao(database: AppDatabase):WalletDao{
        return database.walletDao()
    }


}