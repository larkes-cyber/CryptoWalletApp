package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.UseGetSecretWords
import com.example.tonwalletapp.domain.usecase.UseSaveLocalSecretWords
import com.example.tonwalletapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecoveryPhraseViewModel @Inject constructor(
    private val useGetSecretWords: UseGetSecretWords,
    private val useSaveLocalSecretWords: UseSaveLocalSecretWords
):ViewModel() {

    private val _wordsState = mutableStateOf(RecoveryPhraseState())
    val wordsState = _wordsState

    init {
        useGetSecretWords.invoke().onEach { res ->
            when(res){
                is Resource.Success -> _wordsState.value = RecoveryPhraseState(words = res.data!!)
                is Resource.Loading -> _wordsState.value = RecoveryPhraseState(isLoading = true)
                is Resource.Error -> _wordsState.value = RecoveryPhraseState(error = res.message!!)
            }
        }.launchIn(viewModelScope)
    }

    fun saveWords(){
        useSaveLocalSecretWords.execute(wordsState.value.words)
    }

}