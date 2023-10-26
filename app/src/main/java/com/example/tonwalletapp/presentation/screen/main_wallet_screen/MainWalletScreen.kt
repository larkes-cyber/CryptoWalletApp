package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainWalletScreen(
    navController: NavController,
    viewModel: MainWalletViewModel
) {

    val walletUIState by viewModel.walletUIState.collectAsState()

    LaunchedEffect(walletUIState){
        println(walletUIState.walletDetail)
        if(walletUIState.authStatus == IS_NOT_AUTHORIZED){
            navController.navigate(Screen.StartScreen.route)
        }
    }


    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)) {

            }
        },
        drawerGesturesEnabled = true,
        sheetPeekHeight = 150.dp
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.thirdBackground)) {
            
        }
        
    }

}