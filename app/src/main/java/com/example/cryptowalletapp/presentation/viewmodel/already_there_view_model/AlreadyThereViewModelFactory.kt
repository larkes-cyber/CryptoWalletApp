package com.example.cryptowalletapp.presentation.viewmodel.already_there_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptowalletapp.domain.usecase.UseCheckAlreadyUser
import com.example.cryptowalletapp.domain.usecase.UseGetUserData

class AlreadyThereViewModelFactory(
    val useCheckAlreadyUser: UseCheckAlreadyUser
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlreadyThereViewModel(
            useCheckAlreadyUser = useCheckAlreadyUser
        ) as T
    }
}