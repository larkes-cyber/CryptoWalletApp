package com.example.tonwalletapp.di.data.remote

import com.example.tonwalletapp.data.remote.ton_lite_client.TonLiteClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
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