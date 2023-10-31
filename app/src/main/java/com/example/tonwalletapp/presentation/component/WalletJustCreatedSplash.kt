package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.WALLET_CREATED
import com.example.tonwalletapp.until.Constants.YOUR_ADDRESS

@Composable
fun WalletJustCreatedSplash(
    address:String
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(45.dp))
        Image(
            painter = painterResource(id = R.drawable.ton_bird),
            contentDescription = "",
            modifier = Modifier
                .width(82.dp)
                .height(87.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = WALLET_CREATED,
            fontSize = 24.sp,
            color = AppTheme.colors.secondBackground,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = YOUR_ADDRESS,
            fontSize = 15.sp,
            color = AppTheme.colors.strokeFormColor
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.padding()) {
            
        }
        Box(modifier = Modifier.padding(horizontal = 50.dp)) {
            Text(
                text = address,
                fontSize = 15.sp,
                color = AppTheme.colors.secondBackground,
                textAlign = TextAlign.Center
            )
        }
    }

}