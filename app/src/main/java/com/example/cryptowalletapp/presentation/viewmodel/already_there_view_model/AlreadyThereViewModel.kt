package com.example.cryptowalletapp.presentation.viewmodel.already_there_view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptowalletapp.domain.usecase.UseCheckAlreadyUser
import com.example.cryptowalletapp.domain.usecase.UseGetUserData

class AlreadyThereViewModel(
    val useCheckAlreadyUser: UseCheckAlreadyUser
):ViewModel() {

    private val lifeIsThereResult = MutableLiveData<Boolean>()
    val lifeIsThereResultConst = lifeIsThereResult

   suspend fun checkUser(){
        lifeIsThereResult.value = useCheckAlreadyUser.execute()
    }

}