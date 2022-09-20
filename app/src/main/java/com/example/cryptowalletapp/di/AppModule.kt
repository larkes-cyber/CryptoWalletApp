package com.example.cryptowalletapp.di

import android.content.Context
import com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel.SignUpViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext():Context{
        return context
    }

    @Provides
    fun provideSignInViewModelFactory(): SignUpViewModelFactory {
        return SignUpViewModelFactory()
    }

}