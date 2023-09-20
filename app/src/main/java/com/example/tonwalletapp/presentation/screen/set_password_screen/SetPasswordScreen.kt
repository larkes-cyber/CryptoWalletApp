package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.view.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.view.PasswordProgressBar
import com.example.tonwalletapp.presentation.view.TopBarApp
import com.example.tonwalletapp.until.Constants

@Composable
fun SetPasswordScreen(
    navController: NavController,
    viewModel: SetPasswordViewModel
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarApp() {
            navController.popBackStack()
        }
        InfoScreenSkeleton(
            image = R.drawable.set_password_frame,
            title = Constants.SetPasswordTitle,
            subtitle = Constants.SetPasswordText,
            visibleBtn = false
        )
        Spacer(modifier = Modifier.height(40.dp))
        PasswordProgressBar(progress = 2)

    }

}