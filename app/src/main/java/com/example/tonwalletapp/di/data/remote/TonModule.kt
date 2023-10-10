package com.example.tonwalletapp.di.data.remote

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import com.example.tonwalletapp.data.remote.ton.TonLiteClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TonModule {

    @Provides
    @Singleton
    fun provideTonLiteClient(
        coroutineScope: CoroutineScope
    ):TonLiteClient{
        return TonLiteClientImpl(
            coroutineScope = coroutineScope
        )
    }

}