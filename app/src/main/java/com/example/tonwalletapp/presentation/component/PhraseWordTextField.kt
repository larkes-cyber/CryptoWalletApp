package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhraseWordTextField(
    focused:Boolean = false,
    rangePlace:Int = 0,
    word:String = "",
    onNextClick:() -> Unit = {},
    onWrongValueChange:(Boolean) -> Unit = {},
    onWordChange:(String) -> Unit
    ) {

    val isFormFocused = rememberSaveable {
        mutableStateOf(false)
    }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val focusRequester = remember { FocusRequester() }

    val wrongValue = rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(focused){
        if(focused){
            focusRequester.requestFocus()
        }
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
                onValueChange = {value ->
                    onWordChange(value)
                    onWrongValueChange(value.any { it.isWhitespace() })
                    wrongValue.value = value.any { it.isWhitespace() }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = {
                        onNextClick()
                    }
                ),                textStyle = TextStyle(
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .focusRequester(focusRequester)
                    .bringIntoViewRequester(bringIntoViewRequester)
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
                .background(if(wrongValue.value) Color.Red else if (isFormFocused.value) AppTheme.colors.primaryBackground else AppTheme.colors.strokeFormColor)
                .align(Alignment.BottomEnd)
        )
    }


}