package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

data class RecoveryPhraseState(
    val words:List<String> = listOf(),
    val isLoading:Boolean = false,
    val error:String = ""
)