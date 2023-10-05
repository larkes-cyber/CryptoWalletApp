package com.example.tonwalletapp.di.data.remote

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import java.net.URL
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object TonModule {

    @Provides
    @Singleton
    fun provideTonLiteClient(
        coroutineScope: CoroutineScope
    ):TonLiteClient{
        return TonLiteClient(
            coroutineScope = coroutineScope
        )
    }

}