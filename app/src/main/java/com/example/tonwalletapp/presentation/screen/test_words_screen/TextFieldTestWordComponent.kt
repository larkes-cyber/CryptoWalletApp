package com.example.tonwalletapp.presentation.screen.test_words_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.PrimaryLightGray
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun TextFieldTestWordComponent(
    word:String,
    onChange:(String) -> Unit,
    num:Int
) {

    val focusedState = rememberSaveable() {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.width(215.dp)) {
        Row() {
            Box(modifier = Modifier.padding(bottom = 0.dp)) {
                Text(
                    text = "$num:",
                    fontFamily = roboto,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = PrimaryLightGray
                )
            }

            Spacer(modifier = Modifier.width(4.dp))
            BasicTextField(
                textStyle = TextStyle(
                    fontFamily = roboto,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                cursorBrush = SolidColor(if(focusedState.value) PrimaryLightBlue else PrimaryLightGray),
                decorationBox = {
                    it()
                },
                value = word,
                onValueChange = {
                    onChange(it)
                },
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        focusedState.value = it.isFocused
                    }
            )
        }

        Spacer(modifier = Modifier.height(7.dp))
        Divider(modifier = Modifier
            .background(if(focusedState.value) PrimaryLightBlue else Color(0xFFDBDBDB))
            .height(1.dp)
            .fillMaxWidth()
        )

    }



}