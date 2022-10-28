package com.example.cryptowalletapp.di

import android.content.Context
import com.example.cryptowalletapp.domain.usecase.*
import com.example.cryptowalletapp.presentation.viewmodel.already_there_view_model.AlreadyThereViewModelFactory
import com.example.cryptowalletapp.presentation.viewmodel.home_view_model.HomeViewModelFactory
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
    fun provideAlreadyThereViewModelFactory(
        useCheckAlreadyUser: UseCheckAlreadyUser
    ):AlreadyThereViewModelFactory{
        return AlreadyThereViewModelFactory(
            useCheckAlreadyUser = useCheckAlreadyUser
        )
    }

    @Provides
    fun provideLoadingViewModelFactory(
        useInsertUserData: UseInsertUserData,
        useGetSmallCoinInfo: UseGetSmallCoinInfo
    ):LoadingViewModelFactory{
        return LoadingViewModelFactory(
            useInsertUserData = useInsertUserData,
            useGetSmallCoinInfo = useGetSmallCoinInfo
        )
    }

    @Provides
    fun provideHomeViewModelFactory(
        useGetSmallCoinInfo: UseGetSmallCoinInfo,
        useGetCurrencyLogoBySym: UseGetCurrencyLogoBySym
    ):HomeViewModelFactory{
        return HomeViewModelFactory(
            useGetSmallCoinInfo = useGetSmallCoinInfo,
            useGetCurrencyLogoBySym = useGetCurrencyLogoBySym
        )
    }

}