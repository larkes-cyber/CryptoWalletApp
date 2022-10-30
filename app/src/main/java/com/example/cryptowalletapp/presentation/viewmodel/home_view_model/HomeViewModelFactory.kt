package com.example.cryptowalletapp.presentation.viewmodel.home_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowalletapp.domain.usecase.UseGetCurrencyLogoBySym
import com.example.cryptowalletapp.domain.usecase.UseGetSmallCoinInfo
import com.example.cryptowalletapp.domain.usecase.UseGetTopCoins

class HomeViewModelFactory(
    val useGetSmallCoinInfo: UseGetSmallCoinInfo,
    val useGetCurrencyLogoBySym: UseGetCurrencyLogoBySym,
    val useGetTopCoins: UseGetTopCoins
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            useGetSmallCoinInfo = useGetSmallCoinInfo,
            useGetCurrencyLogoBySym = useGetCurrencyLogoBySym,
            useGetTopCoins = useGetTopCoins
        ) as T
    }
}