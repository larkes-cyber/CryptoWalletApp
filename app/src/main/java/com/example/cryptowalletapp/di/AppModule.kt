package com.example.cryptowalletapp.di

import android.content.Context
import com.example.cryptowalletapp.domain.usecase.UseGetUserData
import com.example.cryptowalletapp.domain.usecase.UseInsertUserData
import com.example.cryptowalletapp.presentation.viewmodel.loading_view_model.LoadingViewModel
import com.example.cryptowalletapp.presentation.viewmodel.loading_view_model.LoadingViewModelFactory
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

    @Provides
    fun provideLoadingViewModelFactory(
        useInsertUserData: UseInsertUserData
    ):LoadingViewModelFactory{
        return LoadingViewModelFactory(
            useInsertUserData = useInsertUserData
        )
    }

}