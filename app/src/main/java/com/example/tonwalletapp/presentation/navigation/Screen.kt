package com.example.tonwalletapp.presentation.navigation

sealed class Screen(val route:String) {
    object StartScreen:Screen("start_screen")
    object CongratsScreen:Screen("congrats_screen")
    object RecoveryPhraseScreen:Screen("recovery_phrase_screen")
    object TestTimeScreen:Screen("test_time_screen")
    object SuccessScreen:Screen("success_screen")
    object SetPasswordScreen:Screen("set_password_screen")
    object ReadyToGoScreen:Screen("ready_to_go_screen")

}