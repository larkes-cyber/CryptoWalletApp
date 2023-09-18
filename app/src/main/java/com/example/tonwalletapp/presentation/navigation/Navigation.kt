package com.example.tonwalletapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tonwalletapp.presentation.screen.start_screen.StartScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.StartScreen.route ){

        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

    }

}