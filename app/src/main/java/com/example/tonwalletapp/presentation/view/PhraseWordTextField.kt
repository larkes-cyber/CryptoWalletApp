package com.example.tonwalletapp.presentation.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun PhraseWordTextField(
    rangePlace:Int = 0,
    word:String = "",
    onWordChange:(String) -> Unit
) {

    val isFormFocused = rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .height(30.dp)
            .width(200.dp)
    ) {
        Row() {
            Text(
                text = "$rangePlace: ",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = AppTheme.colors.strokeFormColor
                )
            )
            BasicTextField(
                value = word,
                onValueChange = {
                    onWordChange(it)
                },
                textStyle = TextStyle(
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .onFocusChanged {
                        isFormFocused.value = it.isFocused
                    },
                cursorBrush = SolidColor(if(isFormFocused.value)AppTheme.colors.primaryBackground else AppTheme.colors.strokeFormColor)
            )
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(if(isFormFocused.value)AppTheme.colors.primaryBackground else AppTheme.colors.strokeFormColor)
                .align(Alignment.BottomEnd)
        )
    }


}