package com.example.cryptowalletapp.presentation.viewmodel.loading_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowalletapp.domain.usecase.UseGetSmallCoinInfo
import com.example.cryptowalletapp.domain.usecase.UseGetUserData
import com.example.cryptowalletapp.domain.usecase.UseInsertUserData

class LoadingViewModelFactory(
    val useInsertUserData: UseInsertUserData,
    val useGetSmallCoinInfo: UseGetSmallCoinInfo
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoadingViewModel(
            useInsertUserData = useInsertUserData,
            useGetSmallCoinInfo = useGetSmallCoinInfo
        ) as T
    }
}