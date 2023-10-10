package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.user_usecase.UseSetupTonConfig
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetAllWalletsId
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWalletDetailInfo
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainWalletViewModel @Inject constructor(
    private val useGetAllWalletsId: UseGetAllWalletsId,
    private val useGetWalletDetailInfo: UseGetWalletDetailInfo,
    private val useSetupTonConfig: UseSetupTonConfig
):ViewModel() {

    private val _walletUIState = MutableStateFlow(WalletUIState())
    val walletUIState:StateFlow<WalletUIState> = _walletUIState
//
    init {
        useSetupTonConfig.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                }
                is Resource.Success -> {
                    setupMainWallet()
                }

                else -> {}
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

    }

    private fun setupMainWallet(){
        useGetAllWalletsId.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                }
                is Resource.Success -> {
                    val wallets = res.data
                    if(res.data!!.isEmpty()){
                        _walletUIState.value = WalletUIState(authStatus = IS_NOT_AUTHORIZED)
                    }else{
                        getWalletDetailInfo(wallets!![0])
                    }
                }
                is Resource.Error -> {
                    _walletUIState.value = WalletUIState(error = res.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWalletDetailInfo(id:String){
        useGetWalletDetailInfo.invoke(id).onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _walletUIState.value = WalletUIState(walletDetail = res.data)
                }
                is Resource.Error -> {
                    _walletUIState.value = WalletUIState(error = res.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }


}