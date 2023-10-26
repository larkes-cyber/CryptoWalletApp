package com.example.tonwalletapp.presentation.screen.ready_to_go_screen

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
import com.example.tonwalletapp.presentation.view.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants

@Composable
fun ReadyToGoScreen(
    navController: NavController
) {

    Column(modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            InfoScreenSkeleton(
                image = R.drawable.success_frame,
                title = Constants.ReadyToGoTitle,
                subtitle = Constants.ReadyToGoText,
                btnTitle = Constants.VisitWalletBtnText,
                visibleBtn = true,
                onBtnClick = {
                    navController.navigate(Screen.MainWalletScreen.route)
                }
            ){
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }

}