package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetTransactionsByAddress
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWalletInfo
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetWallets
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainWalletViewModel @Inject constructor(
    private val useGetWallets: UseGetWallets,
    private val useGetWalletInfo: UseGetWalletInfo,
    private val useGetTransactionsByAddress: UseGetTransactionsByAddress
):ViewModel() {

    private val _walletUIState = MutableStateFlow(WalletUIState())
    val walletUIState:StateFlow<WalletUIState> = _walletUIState

    private val _transactionsUIState = MutableStateFlow(TransactionsUIState())
    val transactionsUIState:StateFlow<TransactionsUIState> = _transactionsUIState
//
    init {
         setupMainWallet()
    }

    private fun setupMainWallet(){
        _walletUIState.value = WalletUIState(isLoading = true)
        useGetWallets.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _walletUIState.value = WalletUIState(isLoading = true)
                    _transactionsUIState.value = TransactionsUIState(isLoading = true)
                }
                is Resource.Success -> {
                    val wallets = res.data
                    if(res.data!!.isEmpty()){
                        _walletUIState.value = WalletUIState(authStatus = IS_NOT_AUTHORIZED)
                    }else{
                        val address = wallets!![0].address
                        getWalletDetailInfo(address)
                        getTransactions(address)
                    }
                }
                is Resource.Error -> {
                    _walletUIState.value = WalletUIState(error = res.message!!)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
    private fun getTransactions(address: String){

        useGetTransactionsByAddress.invoke(address).onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _transactionsUIState.value = TransactionsUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _transactionsUIState.value = TransactionsUIState(txt = res.data ?: listOf())
                }
                is Resource.Error -> {
                    _transactionsUIState.value = TransactionsUIState(error = res.message!!)
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

    }

    fun formatAddress(address:String):String{
        println(address)
        return "${address.take(4)}...${address.takeLast(4)}"
    }

    private fun getWalletDetailInfo(id:String){
        useGetWalletInfo.invoke(id).onEach {res ->
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
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }


}