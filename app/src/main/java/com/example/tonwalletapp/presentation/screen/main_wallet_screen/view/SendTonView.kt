package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.PASTE_ADDRESS_TITLE
import com.example.tonwalletapp.until.Constants.PASTE_BUTTON_TITLE
import com.example.tonwalletapp.until.Constants.SEND_TON_TITLE
import com.example.tonwalletapp.until.Constants.WALLET_ADDRESS_TITLE
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.until.Constants.ContinueBtnText
import com.example.tonwalletapp.until.Constants.SCAN_BUTTON_TITLE

@Composable
fun SendTonView(
    viewController: SendTonViewController = hiltViewModel()
) {

    val addressUIState  by viewController.addressUIState.collectAsState()


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
           color = AppTheme.colors.primaryBackground,
           fontWeight = FontWeight.Medium
       )
       Spacer(modifier = Modifier.height(22.dp))
       AddressTextField(address = addressUIState){addr ->
           viewController.onAddressChange(addr)
       }
       Spacer(modifier = Modifier.height(12.dp))
       Text(
           text = PASTE_ADDRESS_TITLE,
           fontSize = 15.sp,
           color = AppTheme.colors.strokeFormColor
       )
       Spacer(modifier = Modifier.height(13.dp))
       Row(
           horizontalArrangement = Arrangement.spacedBy(24.dp)
       ) {
           OptionButtonWrapper(
               title = PASTE_BUTTON_TITLE,
               icon = R.drawable.paste_icon
           ){

           }
           OptionButtonWrapper(
               title = SCAN_BUTTON_TITLE,
               icon = R.drawable.scan
           ){

           }
       }
       Spacer(modifier = Modifier.height(80.dp))
       PrimaryButtonApp(
           modifier = Modifier.fillMaxWidth(),
           text = ContinueBtnText
       ) {

       }
   }
}

@Composable
fun OptionButtonWrapper(
    title:String,
    icon:Int,
    onClick:() -> Unit = {}
) {
    Button(
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = AppTheme.colors.primaryBackground
            )
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AppTheme.colors.primaryBackground
            )
        }
    }
}