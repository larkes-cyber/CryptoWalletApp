package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun TonPrimaryTextField(
    modifier: Modifier = Modifier,
    singleLine:Boolean = false,
    placeholder: String = "",
    text:String,
    onChange:(String) -> Unit
) {

    val isFormFocused = rememberSaveable {
        mutableStateOf(false)
    }

    Column() {
        BasicTextField(
            value = text,
            onValueChange = {value ->
                onChange(value)
            },
            singleLine = singleLine,
            modifier = modifier
                .onFocusChanged {
                    isFormFocused.value = it.isFocused
                },
            cursorBrush = SolidColor(if(isFormFocused.value) AppTheme.colors.primaryBackground else AppTheme.colors.strokeFormColor),
            textStyle = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        ){
            if(text.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 15.sp,
                    color = AppTheme.colors.strokeFormColor
                )
            }
            it()
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(if (isFormFocused.value) AppTheme.colors.primaryBackground else AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
        )

    }

}