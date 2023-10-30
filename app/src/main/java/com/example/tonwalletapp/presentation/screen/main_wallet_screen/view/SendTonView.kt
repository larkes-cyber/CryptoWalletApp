package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.SEND_TON_TITLE
import com.example.tonwalletapp.until.Constants.WALLET_ADDRESS_TITLE

@Composable
fun SendTonView(

) {
   Column(modifier = Modifier
       .fillMaxWidth()
       .padding(top = 12.dp)
       .padding(horizontal = 20.dp)){
        Text(
            text = SEND_TON_TITLE,
            fontSize = 20.sp,
            color = AppTheme.colors.secondBackground,
            fontWeight = FontWeight.Medium
       )
       Spacer(modifier = Modifier.height(24.dp))
       Text(
           text = WALLET_ADDRESS_TITLE,
           fontSize = 15.sp,
           color = AppTheme.colors.secondBackground,
           fontWeight = FontWeight.Medium
       )

   }
}