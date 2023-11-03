package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.until.Constants.ERROR_PENDING_TITLE
import com.example.tonwalletapp.until.Constants.PENDING_TON_SUBTITLE
import com.example.tonwalletapp.until.Constants.PENDING_TON_TITLE
import com.example.tonwalletapp.until.Constants.PROCESS_PENDING_TITLE
import com.example.tonwalletapp.until.Constants.SUCCESS_PENDING_TITLE
import com.example.tonwalletapp.until.Constants.VIEW_WALLET_BTN_TITLE
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun PendingSubView(
    amount:Float,
    address:String,
    startSending:Flow<Resource<String>>,
    onWalletBack:() -> Unit
) {

    val pendingState = remember {
        mutableStateOf(PROCESS_PENDING_TITLE)
    }


    LaunchedEffect(startSending){
        startSending.onEach {res ->
            when(res){
                is Resource.Success -> {
                    pendingState.value = SUCCESS_PENDING_TITLE
                }
                is Resource.Loading -> {
                    pendingState.value = PROCESS_PENDING_TITLE
                }
                is Resource.Error -> {
                    pendingState.value = ERROR_PENDING_TITLE
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fly_dollars),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(78.dp)
                    .height(67.dp)
            )
            Text(
                text = PENDING_TON_TITLE,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = PENDING_TON_SUBTITLE,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
        }
        PrimaryButtonApp(
            text = VIEW_WALLET_BTN_TITLE,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            onWalletBack()
        }
    }

}