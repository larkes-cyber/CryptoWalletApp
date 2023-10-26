package com.example.tonwalletapp.presentation.screen.import_success_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.view.InfoScreenSkeleton
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants

@Composable
fun ImportSuccessScreen(
    navController: NavController
) {

    Column(modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            InfoScreenSkeleton(
                image = R.drawable.ton_congrats_frame,
                title = Constants.ImportSuccessTitle,
                subtitle = "",
                btnTitle = Constants.ProceedBtnText,
                visibleBtn = true,
                onBtnClick = {
                    navController.navigate(Screen.MainWalletScreen.route)
                }
            ){
                Spacer(modifier = Modifier.height(75.dp))
            }
        }
    }

}