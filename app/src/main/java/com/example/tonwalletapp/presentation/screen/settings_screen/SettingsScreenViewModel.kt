package com.example.tonwalletapp.presentation.screen.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseDeleteWallet
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGetFirstWalletWords
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val useGetFirstWalletWords: UseGetFirstWalletWords,
    private val useDeleteWallet: UseDeleteWallet
):ViewModel() {

    private val _secretWords = MutableStateFlow(listOf<String>())
    val secretWords:StateFlow<List<String>> = _secretWords

    private val _showSecretWordsUIState = MutableStateFlow(false)
    val showSecretWordsUIState:StateFlow<Boolean> = _showSecretWordsUIState

    private val _showDeleteConfirmDialogUIState = MutableStateFlow(false)
    val showDeleteConfirmDialogUIState:StateFlow<Boolean> = _showDeleteConfirmDialogUIState

    private val _walletDeletingHasConfirmedUIState = MutableStateFlow(false)
    val walletDeletingHasConfirmedUIState:StateFlow<Boolean> = _walletDeletingHasConfirmedUIState

    init {
        useGetFirstWalletWords.invoke().onEach {
            if(it is Resource.Success){
                _secretWords.value = it.data!!
            }
        }.launchIn(viewModelScope)
    }

    fun showSecretWords(){
        _showSecretWordsUIState.value = true
    }

    fun hideWords(){
       _showSecretWordsUIState.value = false
    }

    fun showDeleteConfirmDialog(){
        _showDeleteConfirmDialogUIState.value = true
    }
    fun hideDeleteConfirmDialog(){
        _showDeleteConfirmDialogUIState.value = false
    }
    fun deleteWallet(){
        viewModelScope.launch {
            useDeleteWallet.execute()
            _walletDeletingHasConfirmedUIState.value = true
        }
    }

}