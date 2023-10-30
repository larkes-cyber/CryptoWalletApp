package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SendTonViewController @Inject constructor():ViewModel() {

    private val _addressUIState = MutableStateFlow("")
    val addressUIState:StateFlow<String> = _addressUIState

    fun onAddressChange(address:String){
        _addressUIState.value = address
    }

}