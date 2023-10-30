package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.ADDRESS_TEXT_FIELD_PLACEHOLDER
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressTextField(
    placeholder:String = ADDRESS_TEXT_FIELD_PLACEHOLDER,
    address: String,
    onaAddressChange:(String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current


    Column {
        BasicTextField(
            value = address,
            onValueChange = {value ->
                onaAddressChange(value)
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.secondPrimaryTitle
            ),
            singleLine = true,
            modifier = Modifier
                .focusRequester(focusRequester),
            cursorBrush = SolidColor(AppTheme.colors.primaryBackground)
        ){
            if(address.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 15.sp,
                    color = AppTheme.colors.strokeFormColor
                )
            }
            it()
        }
        Spacer(modifier = Modifier.height(30.dp))
        Divider(
            modifier = Modifier
                .background(AppTheme.colors.primaryBackground)
                .fillMaxWidth()
                .height(2.dp)
        )
    }

    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
        delay(100) // Make sure you have delay here
        keyboard?.show()
    }


}