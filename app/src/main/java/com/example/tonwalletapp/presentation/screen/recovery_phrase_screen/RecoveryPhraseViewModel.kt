package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.wallet_usecase.UseGenerateSecretWords
import com.example.tonwalletapp.until.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecoveryPhraseViewModel @Inject constructor(
    private val useGenerateSecretWords: UseGenerateSecretWords
):ViewModel() {

    private val _phraseWordsUIState = MutableStateFlow(PhraseWordsUIState())
    val phraseWordsUIState:StateFlow<PhraseWordsUIState> = _phraseWordsUIState

    private val _activeTimeAlertDialogUIState = MutableStateFlow(false)
    val activeTimeAlertDialogUIState:StateFlow<Boolean> = _activeTimeAlertDialogUIState

    private val _hasBeenDoneUIState = MutableStateFlow(false)
    val hasBeenDoneUIState:StateFlow<Boolean> = _hasBeenDoneUIState

    init {
        fetchWords()
    }

    private fun fetchWords(){
        useGenerateSecretWords.invoke().onEach {res ->
            when(res){
                is Resource.Loading -> {
                    _phraseWordsUIState.value = PhraseWordsUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _phraseWordsUIState.value = PhraseWordsUIState(words = res.data!!)
                }
                is Resource.Error -> {
                    _phraseWordsUIState.value = PhraseWordsUIState(error = res.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onDone(){
        _activeTimeAlertDialogUIState.value = true
    }

    fun dismissDialog(){
        _activeTimeAlertDialogUIState.value = false
    }

}