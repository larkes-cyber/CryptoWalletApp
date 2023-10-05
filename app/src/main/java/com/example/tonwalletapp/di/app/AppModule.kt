package com.example.tonwalletapp.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context):Context = context

    @Provides
    fun provideCoroutineContest():CoroutineContext{
        return Dispatchers.IO
    }

    @Provides
    fun provideCoroutineScope():CoroutineScope{
        return CoroutineScope(Dispatchers.IO + Job())
    }

}