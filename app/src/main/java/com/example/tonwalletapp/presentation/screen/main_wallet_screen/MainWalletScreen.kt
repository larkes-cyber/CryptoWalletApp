package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.IS_NOT_AUTHORIZED
import com.example.tonwalletapp.until.Constants.RECEIVE_BTN_TITLE
import com.example.tonwalletapp.until.Constants.SEND_BTN_TITLE

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

    val scrollableState = rememberScrollState()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.secondBackground)
            .verticalScroll(scrollableState)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(text = "")
            }
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp), modifier = Modifier.padding(horizontal = 12.dp)) {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.scan),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = AppTheme.colors.background
                    )
                }
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.setting),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = AppTheme.colors.background
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 76.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(57.dp)
        ) {
            if(walletUIState.walletDetail != null) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(11.dp)
                ) {
                    Text(
                        text = walletUIState.walletDetail!!.address,
                        fontSize = 15.sp,
                        color = AppTheme.colors.background
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ton_crystal_frame),
                            contentDescription = "",
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = walletUIState.walletDetail!!.balance.toString(),
                            fontSize = 44.sp,
                            color = AppTheme.colors.background,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            if(walletUIState.isLoading){
                Image(
                    painter = painterResource(id = R.drawable.ton_crystal_frame),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                PrimaryButtonApp(
                    text = RECEIVE_BTN_TITLE,
                    icon = R.drawable.receive,
                    modifier = Modifier.weight(1f)
                ) {

                }
                PrimaryButtonApp(
                    text = SEND_BTN_TITLE,
                    icon = R.drawable.send,
                    modifier = Modifier.weight(1f)
                ) {

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.background)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Column(modifier = Modifier.padding(top = 20.dp).height(800.dp)) {

                }
            }
        }

    }

}