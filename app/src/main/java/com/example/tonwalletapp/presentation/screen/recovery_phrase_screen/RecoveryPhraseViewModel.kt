package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecoveryPhraseViewModel @Inject constructor():ViewModel() {

    private val _phraseWords = MutableStateFlow((1..20).map { "network" })
    val phraseWords:StateFlow<List<String>> = _phraseWords

    private val _activeTimeAlertDialogUIState = MutableStateFlow(false)
    val activeTimeAlertDialogUIState:StateFlow<Boolean> = _activeTimeAlertDialogUIState

    private val _hasBeenDoneUIState = MutableStateFlow(false)
    val hasBeenDoneUIState:StateFlow<Boolean> = _hasBeenDoneUIState


    fun onDone(){
        _activeTimeAlertDialogUIState.value = true
    }

    fun dismissDialog(){
        _activeTimeAlertDialogUIState.value = false
    }

}