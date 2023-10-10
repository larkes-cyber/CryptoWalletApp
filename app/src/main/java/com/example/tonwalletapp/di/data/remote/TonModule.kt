package com.example.tonwalletapp.di.data.remote

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import com.example.tonwalletapp.data.remote.ton.TonLiteClientConfig
import com.example.tonwalletapp.data.remote.ton.TonLiteClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TonModule {

    @Singleton
    @Provides
    fun provideTonLiteClientConfig(
        httpClient: HttpClient
    ):TonLiteClientConfig{
        return TonLiteClientConfig(httpClient)
    }

    @Singleton
    @Provides
    fun provideTonLiteClient(
        coroutineScope: CoroutineScope,
        tonLiteClientConfig: TonLiteClientConfig
    ):TonLiteClient{
        return TonLiteClientImpl(
            coroutineScope = coroutineScope,
             tonLiteClientConfig = tonLiteClientConfig
        )
    }

}