package com.example.cryptowalletapp.di

import android.content.Context
import androidx.room.Room
import com.example.cryptowalletapp.common.Constants.CRYPTO_PRICE_URL
import com.example.cryptowalletapp.common.Constants.CRYPTO_URL
import com.example.cryptowalletapp.data.database.AppDatabase
import com.example.cryptowalletapp.data.database.dao.UserDataDao
import com.example.cryptowalletapp.data.database.repository.DatabaseRepository
import com.example.cryptowalletapp.data.repositoty.AuthRepositoryData
import com.example.cryptowalletapp.data.repositoty.CoinRepositoryData
import com.example.cryptowalletapp.data.retrofit.api.CompareApi
import com.example.cryptowalletapp.data.retrofit.api.PaprikaApi
import com.example.cryptowalletapp.data.retrofit.repository.RetrofitRepository
import com.example.cryptowalletapp.domain.repository.AuthRepository
import com.example.cryptowalletapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRetrofitRepository(
        paprikaApi: PaprikaApi,
        compareApi: CompareApi
    ):RetrofitRepository{
        return RetrofitRepository(
            paprikaApi = paprikaApi,
            compareApi = compareApi
        )
    }

    @Singleton
    @Provides
    fun provideCoinRepository(
        retrofitRepository: RetrofitRepository
    ):CoinRepository{
        return CoinRepositoryData(
            retrofitRepository = retrofitRepository
        )
    }

    @Singleton
    @Provides
    fun provideApi(
    ):PaprikaApi{

        val retrofit = Retrofit.Builder()
            .baseUrl(CRYPTO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PaprikaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCryptoPriceApi(
    ):CompareApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(CRYPTO_PRICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CompareApi::class.java)
    }


}