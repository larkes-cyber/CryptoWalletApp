package com.example.tonwalletapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tonwalletapp.presentation.screen.all_set_screen.AllSetScreen
import com.example.tonwalletapp.presentation.screen.confirm_pass_screen.ConfirmPassScreen
import com.example.tonwalletapp.presentation.screen.congratulations_screen.CongratulationsScreen
import com.example.tonwalletapp.presentation.screen.perfect_screen.PerfectScreen
import com.example.tonwalletapp.presentation.screen.recovery_phrase_screen.RecoveryPhraseScreen
import com.example.tonwalletapp.presentation.screen.set_password_screen.SetPasscodeScreen
import com.example.tonwalletapp.presentation.screen.test_words_screen.TestWordsScreen
import com.example.tonwalletapp.presentation.screen.welcome_screen.WelcomeScreen

@Composable
fun Navigate(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route){
        composable(route = Screen.WelcomeScreen.route){
            WelcomeScreen(navController)
        }
        composable(route = Screen.CongratulationsScreen.route){
            CongratulationsScreen(navController)
        }
        composable(route = Screen.RecoveryPhraseScreen.route){
            RecoveryPhraseScreen(navController = navController)
        }
        composable(route = Screen.TestWordsScreen.route){
            TestWordsScreen(navController = navController)
        }
        composable(route = Screen.PerfectScreen.route){
            PerfectScreen(navController = navController)
        }
        composable(route = Screen.SetPasscodeScreen.route){
            SetPasscodeScreen(navController = navController)
        }
        composable(
            route = Screen.ConfirmPassScreen.route + "/{password}",
            arguments = listOf(
                navArgument("password"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){entry ->
            ConfirmPassScreen(navController = navController, password = entry.arguments?.getString("password")!!)
        }
        
        composable(route = Screen.AllSetScreen.route){
            AllSetScreen(navController = navController)
        }
    }


}