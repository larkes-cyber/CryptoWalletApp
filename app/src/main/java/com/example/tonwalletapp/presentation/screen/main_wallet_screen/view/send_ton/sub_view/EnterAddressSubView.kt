package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.InvalidAddressAlert
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.component.AddressTextField
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.OptionButtonWrapper
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.PASTE_ADDRESS_TITLE
import com.example.tonwalletapp.until.Constants.PASTE_BUTTON_TITLE
import com.example.tonwalletapp.until.Constants.SCAN_BUTTON_TITLE
import com.example.tonwalletapp.until.Constants.WALLET_ADDRESS_TITLE
import com.example.tonwalletapp.until.getFromClipBoard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EnterAddressSubView(
    address:String,
    onAddressChange:(String) -> Unit,
    invalidAddrExp:Boolean = false,
    onScanAddress:() -> Unit,
    onDone:() -> Unit,
    onCloseClick:() -> Unit
) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current

    Column() {
        Text(
            text = WALLET_ADDRESS_TITLE,
            fontSize = 15.sp,
            color = AppTheme.colors.primaryBackground,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(22.dp))
        AddressTextField(address = address, keyboard = keyboard!!){addr ->
            onAddressChange(addr)
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
                val copiedText = context.getFromClipBoard()
                if(copiedText != null) onAddressChange(copiedText)
            }
            OptionButtonWrapper(
                title = SCAN_BUTTON_TITLE,
                icon = R.drawable.scan
            ){
                keyboard.hide()
                onScanAddress()
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Box {
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                PrimaryButtonApp(
                    modifier = Modifier.fillMaxWidth(),
                    text = Constants.ContinueBtnText
                ) {
                    onDone()
                }
            }
            if(invalidAddrExp){
                Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                    InvalidAddressAlert(){
                        onCloseClick()
                    }
                }
            }
        }
    }
}