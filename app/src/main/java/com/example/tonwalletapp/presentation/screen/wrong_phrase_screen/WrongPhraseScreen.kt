package com.example.tonwalletapp.presentation.screen.wrong_phrase_screen

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
import com.example.tonwalletapp.until.Constants

@Composable
fun WrongPhraseScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.background).padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        InfoScreenSkeleton(
            image = R.drawable.bad_emoji_frame,
            title = Constants.WrongPhraseTitle,
            subtitle = Constants.WrongPhraseText,
            btnTitle = Constants.EnterWordsBtnTitle,
            optionalBtnTitle = Constants.CreateNewWalletBtnTitle,
            visibleOptionalBtn = true,
            visibleBtn = true,
            onBtnClick = {
                navController.navigate(Screen.ImportPhraseScreen.route)
            },
            onOptionBtnClick = {
                navController.navigate(Screen.CongratsScreen.route)
            }
        ){
            Spacer(modifier = Modifier.height(100.dp))
        }
    }

}