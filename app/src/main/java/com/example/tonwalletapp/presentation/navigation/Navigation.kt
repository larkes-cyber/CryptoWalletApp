package com.example.tonwalletapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tonwalletapp.presentation.screen.congrats_screen.CongratsScreen
import com.example.tonwalletapp.presentation.screen.congrats_screen.CongratsViewModel
import com.example.tonwalletapp.presentation.screen.import_phrase_screen.ImportPhraseScreen
import com.example.tonwalletapp.presentation.screen.import_phrase_screen.ImportPhraseViewModel
import com.example.tonwalletapp.presentation.screen.import_success_screen.ImportSuccessScreen
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.MainWalletScreen
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.MainWalletViewModel
import com.example.tonwalletapp.presentation.screen.ready_to_go_screen.ReadyToGoScreen
import com.example.tonwalletapp.presentation.screen.recovery_phrase_screen.RecoveryPhraseScreen
import com.example.tonwalletapp.presentation.screen.recovery_phrase_screen.RecoveryPhraseViewModel
import com.example.tonwalletapp.presentation.screen.set_password_screen.SetPasswordScreen
import com.example.tonwalletapp.presentation.screen.set_password_screen.SetPasswordViewModel
import com.example.tonwalletapp.presentation.screen.start_screen.StartScreen
import com.example.tonwalletapp.presentation.screen.success_screen.SuccessScreen
import com.example.tonwalletapp.presentation.screen.test_time_screen.TestTimeScreen
import com.example.tonwalletapp.presentation.screen.test_time_screen.TestTimeViewModel
import com.example.tonwalletapp.presentation.screen.wrong_phrase_screen.WrongPhraseScreen
import com.example.tonwalletapp.presentation.splash_screen.SplashScreen
import com.example.tonwalletapp.presentation.splash_screen.SplashScreenViewModel

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){

        composable(Screen.SplashScreen.route){
            val viewModel:SplashScreenViewModel = hiltViewModel()
            SplashScreen(viewModel = viewModel, navController = navController)
        }

        composable(Screen.StartScreen.route){
            StartScreen(navController = navController)
        }

        composable(Screen.CongratsScreen.route){
            val viewModel:CongratsViewModel = hiltViewModel()
            CongratsScreen(
                navController = navController,
                viewModel = viewModel
            )
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
        composable(
            route = Screen.SetPasswordScreen.route + "/{walletAction}",
            arguments = listOf(
                navArgument("walletAction"){
                    type = NavType.StringType
                    defaultValue = "none"
                }
            )
        ){
            val viewModel:SetPasswordViewModel = hiltViewModel()
            val walletAct = it.arguments!!.getString("walletAction")
            SetPasswordScreen(navController = navController, viewModel = viewModel, walletAction = walletAct!!)
        }
        composable(Screen.ReadyToGoScreen.route){
            ReadyToGoScreen(navController = navController)
        }
        composable(Screen.ImportPhraseScreen.route){
            val viewModel:ImportPhraseViewModel = hiltViewModel()
            ImportPhraseScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.ImportSuccessScreen.route)
        {
            ImportSuccessScreen(navController = navController)
        }
        composable(Screen.WrongPhraseScreen.route){
            WrongPhraseScreen(navController = navController)
        }
        composable(Screen.MainWalletScreen.route){

            val viewModel:MainWalletViewModel = hiltViewModel()

            MainWalletScreen(navController = navController, viewModel = viewModel)
        }

    }

}