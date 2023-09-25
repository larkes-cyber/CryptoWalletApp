package com.example.tonwalletapp.presentation.screen.import_phrase_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ImportPhraseViewModel @Inject constructor():ViewModel() {

    private val _phraseUIState = MutableStateFlow((0..23).map { "" })
    val phraseUIState:StateFlow<List<String>> = _phraseUIState

    private val _currentFocusPositionUIState = MutableStateFlow(0)
    val currentFocusPositionUIState:StateFlow<Int> = _currentFocusPositionUIState

    fun onPhraseChange(position:Int, word:String){
        val newPhrase = _phraseUIState.value.toMutableList()
        newPhrase[position-1] = word
        _phraseUIState.value = newPhrase
    }

    fun onCurrentPosition(position: Int){
        _currentFocusPositionUIState.value = position
    }

}