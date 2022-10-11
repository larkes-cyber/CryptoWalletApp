package com.example.cryptowalletapp.presentation.viewmodel.loading_view_model

import androidx.lifecycle.ViewModel
import com.example.cryptowalletapp.domain.model.UserData
import com.example.cryptowalletapp.domain.usecase.UseGetSmallCoinInfo
import com.example.cryptowalletapp.domain.usecase.UseGetUserData
import com.example.cryptowalletapp.domain.usecase.UseInsertUserData

class LoadingViewModel(
    val useInsertUserData: UseInsertUserData,
    val useGetSmallCoinInfo: UseGetSmallCoinInfo
):ViewModel() {

    suspend fun saveData(userData: UserData){
        useInsertUserData.execute(userData)
    }


}