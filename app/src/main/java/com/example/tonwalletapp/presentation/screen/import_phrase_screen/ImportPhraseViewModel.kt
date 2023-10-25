package com.example.tonwalletapp.presentation.screen.import_phrase_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseImportWallet
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImportPhraseViewModel @Inject constructor(
    private val useImportWallet: UseImportWallet
):ViewModel() {

    private val _phraseUIState = MutableStateFlow((0..23).map { "" })
    val phraseUIState:StateFlow<List<String>> = _phraseUIState

    private val _currentFocusPositionUIState = MutableStateFlow(0)
    val currentFocusPositionUIState:StateFlow<Int> = _currentFocusPositionUIState

    private val _walletUIState = MutableStateFlow(WalletState())
    val walletUIState:StateFlow<WalletState> = _walletUIState

    fun onPhraseChange(position:Int, word:String){
        val newPhrase = _phraseUIState.value.toMutableList()
        newPhrase[position-1] = word
        _phraseUIState.value = newPhrase
    }

    fun onCurrentPosition(position: Int){
        _currentFocusPositionUIState.value = position
    }

    fun onFinish(){
        if(_walletUIState.value.errorWords.isEmpty() && _phraseUIState.value.none { it.isEmpty() }){
            useImportWallet.invoke(_phraseUIState.value).onEach { res ->
                when(res){
                    is Resource.Loading -> {
                        _walletUIState.value = WalletState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _walletUIState.value = WalletState(success = true)
                    }
                    is Resource.Error -> {
                        _walletUIState.value = WalletState(error = res.message!!)
                    }
                }
            }.launchIn(viewModelScope)
        }else{
            _walletUIState.value = walletUIState.value.copy(error = "incorrect words")
        }
    }

    fun onWrongValueError(num:Int, bool:Boolean){
        val errorWords = _walletUIState.value.errorWords.toMutableList()
        if(bool && errorWords.any { it == num }) return
        if(bool){
            errorWords.add(num)
        } else {
            errorWords.remove(num)
        }

        Log.d("sdfdsfsdfsdf",_walletUIState.value.errorWords.toString())

        _walletUIState.value = walletUIState.value.copy(errorWords = errorWords)
    }

    fun resetWalletState(){
        _walletUIState.value = WalletState()
    }

}