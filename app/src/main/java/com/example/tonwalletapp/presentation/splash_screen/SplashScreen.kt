package com.example.tonwalletapp.presentation.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.tonwalletapp.presentation.main_activity.LoadingComponent
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.until.Constants.IS_AUTHORIZED
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel,
    navController: NavController
) {

    val authUIState by viewModel.isAuthorizedUIState.collectAsState()
    val passcodeUIState by viewModel.passCodeUIState.collectAsState()


    LaunchedEffect(authUIState){
        if(authUIState == IS_AUTHORIZED && passcodeUIState.isNotEmpty()){
            navController.navigate(Screen.EnterPassCodeScreen.withArgs(passcodeUIState))
        }
        if(authUIState == IS_NOT_AUTHORIZED){
            navController.navigate(Screen.StartScreen.route)
        }
    }

    LoadingComponent()
    
}