package com.example.tonwalletapp.presentation.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.user_usecase.UseGetPassCode
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWallets
import com.example.tonwalletapp.until.Constants.IS_AUTHORIZED
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Constants.NOT_STATED_AUTH_STATUS
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val useGetWallets: UseGetWallets,
    private val useGetPassCode: UseGetPassCode
):ViewModel() {

    private val _isAuthorizedUIState = MutableStateFlow(NOT_STATED_AUTH_STATUS)
    val isAuthorizedUIState: StateFlow<Int> = _isAuthorizedUIState

    private val _passCodeUIState = MutableStateFlow("")
    val passCodeUIState:StateFlow<String> = _passCodeUIState

    init {
        checkAuth()
    }

    private fun checkAuth(){
        useGetWallets.invoke().onEach {res ->
            when(res){
                is Resource.Success -> {
                    _isAuthorizedUIState.value =if(res.data!!.isEmpty()) IS_NOT_AUTHORIZED  else IS_AUTHORIZED
                    if(_isAuthorizedUIState.value == IS_AUTHORIZED){
                       val passcode = useGetPassCode.execute()
                        _passCodeUIState.value = passcode
                    }
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

}