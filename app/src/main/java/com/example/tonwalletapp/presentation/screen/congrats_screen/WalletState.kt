package com.example.tonwalletapp.presentation.screen.congrats_screen


data class WalletState(
    val isLoading:Boolean = false,
    val finished:Boolean = false,
    val error:String = ""
)