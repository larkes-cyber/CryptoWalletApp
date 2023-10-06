package com.example.tonwalletapp.presentation.screen.import_phrase_screen

data class WalletState(
    val isLoading:Boolean = false,
    val success:Boolean = false,
    val error:String = ""
)