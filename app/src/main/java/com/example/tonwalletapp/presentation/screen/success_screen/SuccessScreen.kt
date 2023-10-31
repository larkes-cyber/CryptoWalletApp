package com.example.tonwalletapp.presentation.screen.success_screen

import androidx.compose.foundation.background
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
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.component.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.PerfectBtnText
import com.example.tonwalletapp.until.Constants.PerfectText
import com.example.tonwalletapp.until.Constants.PerfectTitle

@Composable
fun SuccessScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)) {
        TopBarApp() {
           navController.popBackStack()
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            InfoScreenSkeleton(
                image = R.drawable.success_frame,
                title = PerfectTitle,
                subtitle = PerfectText,
                btnTitle = PerfectBtnText,
                visibleBtn = true,
                onBtnClick = {
                }
            ){
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }

}