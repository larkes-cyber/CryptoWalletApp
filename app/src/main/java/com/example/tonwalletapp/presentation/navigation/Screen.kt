package com.example.tonwalletapp.presentation.navigation

sealed class Screen(val route:String) {

    object WelcomeScreen:Screen("welcome")
    object CongratulationsScreen:Screen("congratulations")
    object RecoveryPhraseScreen:Screen("recovery_phrase_screen")
    object TestWordsScreen:Screen("test_words_screen")

    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}