package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.component.DigitalKeyboard
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.component.PasswordProgressBar
import com.example.tonwalletapp.presentation.component.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.ConfirmPasswordTitle
import com.example.tonwalletapp.until.Constants.WalletActionCreate

@Composable
fun SetPasswordScreen(
    navController: NavController,
    viewModel: SetPasswordViewModel,
    walletAction:String
) {

    val passwordUIState by viewModel.passwordUIState.collectAsState()
    val hasBeenDoneUIState by viewModel.hasBeenDoneUIState.collectAsState()

    LaunchedEffect(hasBeenDoneUIState){
        if(hasBeenDoneUIState){
            if(walletAction == WalletActionCreate) navController.navigate(Screen.ReadyToGoScreen.route)
            else navController.navigate(Screen.ImportSuccessScreen.route)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)
    ) {
        TopBarApp() {
            if(passwordUIState.confirmPassword == null) navController.popBackStack()
            else viewModel.resetPassword()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfoScreenSkeleton(
                image = R.drawable.set_password_frame,
                title = if(passwordUIState.confirmPassword != null) ConfirmPasswordTitle else Constants.SetPasswordTitle,
                subtitle = Constants.SetPasswordText,
                visibleBtn = false
            )
            Spacer(modifier = Modifier.height(40.dp))
            PasswordProgressBar(progress = passwordUIState.password.length)
            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 15.dp)) {
                DigitalKeyboard(
                    onBtnClick = {letter ->
                         viewModel.onNewPassSymbol(letter.toString())
                    },
                    onDeleteBtnClick = {
                         viewModel.deleteLastChar()
                    }
                )
            }
        }
    }

}