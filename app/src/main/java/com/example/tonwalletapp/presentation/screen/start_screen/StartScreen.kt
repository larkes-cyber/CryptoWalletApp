package com.example.tonwalletapp.presentation.screen.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.view.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.presentation.view.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.CreateWalletBtnText
import com.example.tonwalletapp.until.Constants.ImportWalletBtnText
import com.example.tonwalletapp.until.Constants.TonWalletText
import com.example.tonwalletapp.until.Constants.TonWalletTitle

@Composable
fun StartScreen(navController: NavController) {
        Box(
            modifier = Modifier.padding(horizontal = 30.dp).fillMaxSize(),
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
                onOptionBtnClick = {}
            ){
                Spacer(modifier = Modifier.height(100.dp))
            }
    }
}