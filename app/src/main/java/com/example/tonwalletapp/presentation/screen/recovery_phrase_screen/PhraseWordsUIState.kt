package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

data class PhraseWordsUIState(
    val isLoading:Boolean = false,
    val words:List<String> = listOf(),
    val error:String = ""
)