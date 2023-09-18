package com.example.tonwalletapp.presentation.navigation

sealed class Screen(val route:String) {

    object StartScreen:Screen("start_screen")

}