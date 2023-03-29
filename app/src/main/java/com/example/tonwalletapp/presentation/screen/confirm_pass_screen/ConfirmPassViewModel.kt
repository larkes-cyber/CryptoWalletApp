package com.example.tonwalletapp.presentation.screen.confirm_pass_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmPassViewModel @Inject constructor():ViewModel() {

    private val _passwordState = mutableStateOf("")
    val passwordState = _passwordState

    private val _hasBeenConfirmed = mutableStateOf(false)
    val hasBeenConfirmed = _hasBeenConfirmed

    fun editPassword(pass:String, correctPass:String){
        _passwordState.value = pass
        if(correctPass == pass) _hasBeenConfirmed.value = true
    }

    fun removeLastSymbol(){
        if(_passwordState.value.isEmpty()) return
        _passwordState.value = passwordState.value.substring(0, passwordState.value.length-1)
    }


}