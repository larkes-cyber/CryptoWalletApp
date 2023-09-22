package com.example.tonwalletapp.presentation.screen.set_password_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetPasswordViewModel @Inject constructor():ViewModel() {

    private val _passwordUIState = MutableStateFlow(PasswordUIState())
    val passwordUIState:StateFlow<PasswordUIState> = _passwordUIState

    private val _hasBeenDoneUIState = MutableStateFlow(false)
    val hasBeenDoneUIState:StateFlow<Boolean> = _hasBeenDoneUIState
    fun onNewPassSymbol(letter:String){
        viewModelScope.launch {
            _passwordUIState.value = passwordUIState.value.copy(password =  passwordUIState.value.password + letter,)
            delay(200)
            if(passwordUIState.value.password.length == 4 && passwordUIState.value.confirmPassword == null){
                _passwordUIState.value = PasswordUIState(confirmPassword = passwordUIState.value.password)
            }else if(passwordUIState.value.password.length >= 4){
                if(passwordUIState.value.confirmPassword == passwordUIState.value.password){
                    _hasBeenDoneUIState.value = true
                    resetPassword()
                }else{
                    _passwordUIState.value = passwordUIState.value.copy(password = "")
                }
            }
        }
    }

    fun deleteLastChar(){
        if(passwordUIState.value.password.isNotEmpty()){
            val str = passwordUIState.value.password.dropLast(1)
            _passwordUIState.value = passwordUIState.value.copy(password = str)
        }
    }

    fun resetPassword(){
        _passwordUIState.value = PasswordUIState()
    }

}