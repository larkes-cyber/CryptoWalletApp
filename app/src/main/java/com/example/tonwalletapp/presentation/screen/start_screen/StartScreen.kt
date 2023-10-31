package com.example.tonwalletapp.presentation.screen.start_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.CreateWalletBtnText
import com.example.tonwalletapp.until.Constants.ImportWalletBtnText
import com.example.tonwalletapp.until.Constants.TonWalletText
import com.example.tonwalletapp.until.Constants.TonWalletTitle

@Composable
fun StartScreen(navController: NavController) {
        Box(
            modifier = Modifier.fillMaxSize().background(AppTheme.colors.background).padding(horizontal = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            InfoScreenSkeleton(
                image = R.drawable.ton_crystal_frame,
                title = TonWalletTitle,
                subtitle = TonWalletText,
                btnTitle = CreateWalletBtnText,
                optionalBtnTitle = ImportWalletBtnText,
                visibleOptionalBtn = true,
                visibleBtn = true,
                onBtnClick = {
                    navController.navigate(Screen.CongratsScreen.route)
                },
                onOptionBtnClick = {
                    navController.navigate(Screen.ImportPhraseScreen.route)
                }
            ){
                Spacer(modifier = Modifier.height(100.dp))
            }
    }
}