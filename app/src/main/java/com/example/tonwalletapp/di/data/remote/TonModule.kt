package com.example.tonwalletapp.di.data.remote

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import com.example.tonwalletapp.data.remote.ton.TonLiteClientFactory
import com.example.tonwalletapp.data.remote.ton.TonLiteClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TonModule {

    @Singleton
    @Provides
    fun provideTonLiteClientFactory(
        httpClient: HttpClient
    ):TonLiteClientFactory{
        return TonLiteClientFactory(httpClient = httpClient)
    }

    @Singleton
    @Provides
    fun provideTonLiteClient(
        tonLiteClientFactory: TonLiteClientFactory
    ):TonLiteClient{
        return TonLiteClientImpl(
            tonLiteClientFactory = tonLiteClientFactory
        )
    }

}