package com.example.tonwalletapp.presentation.screen.set_password_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SetPasscodeViewModel @Inject constructor(
):ViewModel() {

    private val _passwordState = mutableStateOf("")
    val passwordState = _passwordState

    private val _showPassOptionsState = mutableStateOf(false)
    val showPassOptionsState = _showPassOptionsState

    private val _passLenState = mutableStateOf(4)
    val passLenState = _passLenState

    private val _hasBeenFilled = mutableStateOf(false)
    val hasBeenFilled = _hasBeenFilled


    fun editPassword(pass:String){
        _passwordState.value = pass
        if((pass.length == 4) || (pass.length == 6)) {
            _hasBeenFilled.value = true
        }
    }

    fun removeLastSymbol(){
        if(_passwordState.value.isEmpty()) return
        _passwordState.value = passwordState.value.substring(0, passwordState.value.length-1)
    }

    fun setShowPassOptionsState(bool:Boolean) {
        _showPassOptionsState.value = bool
    }
    fun setPassLen(count:Int){
        _passwordState.value = ""
        _passLenState.value = count
    }

}