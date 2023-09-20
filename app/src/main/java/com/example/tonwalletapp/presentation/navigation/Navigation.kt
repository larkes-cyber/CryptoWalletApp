package com.example.tonwalletapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tonwalletapp.presentation.screen.congrats_screen.CongratsScreen
import com.example.tonwalletapp.presentation.screen.recovery_phrase_screen.RecoveryPhraseScreen
import com.example.tonwalletapp.presentation.screen.recovery_phrase_screen.RecoveryPhraseViewModel
import com.example.tonwalletapp.presentation.screen.set_password_screen.SetPasswordScreen
import com.example.tonwalletapp.presentation.screen.set_password_screen.SetPasswordViewModel
import com.example.tonwalletapp.presentation.screen.start_screen.StartScreen
import com.example.tonwalletapp.presentation.screen.success_screen.SuccessScreen
import com.example.tonwalletapp.presentation.screen.test_time_screen.TestTimeScreen
import com.example.tonwalletapp.presentation.screen.test_time_screen.TestTimeViewModel

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.SetPasswordScreen .route ){

        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

        composable(Screen.CongratsScreen.route){
            CongratsScreen(navController = navController)
        }
        composable(Screen.RecoveryPhraseScreen.route){
            val viewModel:RecoveryPhraseViewModel = hiltViewModel()
            RecoveryPhraseScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.TestTimeScreen.route){
            val viewModel:TestTimeViewModel = hiltViewModel()
            TestTimeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.SuccessScreen.route){
            SuccessScreen(navController = navController)
        }
        composable(Screen.SetPasswordScreen.route){
            val viewModel:SetPasswordViewModel = hiltViewModel()
            SetPasswordScreen(navController = navController, viewModel = viewModel)
        }
        

    }

}