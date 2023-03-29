package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tonwalletapp.domain.usecase.UseGetSecretWords
import com.example.tonwalletapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecoveryPhraseViewModel @Inject constructor(
    private val useGetSecretWords: UseGetSecretWords
):ViewModel() {

    init {
        useGetSecretWords.invoke().onEach { res ->
            when(res){
                is Resource.Success -> Log.d("sfsdfsdf",res.data.toString())
            }
        }.launchIn(viewModelScope)
    }

}