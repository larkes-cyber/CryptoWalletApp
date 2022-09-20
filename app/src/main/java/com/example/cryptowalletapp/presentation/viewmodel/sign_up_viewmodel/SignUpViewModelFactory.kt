package com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SignUpViewModelFactory(

): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignUpViewModel() as T
    }
}