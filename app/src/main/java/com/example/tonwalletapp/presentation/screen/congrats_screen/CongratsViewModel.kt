package com.example.tonwalletapp.presentation.screen.congrats_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseSetupWallet
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CongratsViewModel @Inject constructor(
    private val useSetupWallet: UseSetupWallet
):ViewModel() {

    private val _walletUIState = MutableStateFlow(WalletState())
    val walletUIState:StateFlow<WalletState> = _walletUIState

    init {
        useSetupWallet.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> _walletUIState.value = WalletState(isLoading = true)
                is Resource.Success -> _walletUIState.value = WalletState(finished = true)
                is Resource.Error -> _walletUIState.value = WalletState(error = res.message!!)
            }
        }.launchIn(viewModelScope)
    }

}