package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun TextButtonComponent(
    text:String,
    callback:() -> Unit
) {
    ClickableText(
        text = AnnotatedString(text),
        onClick = {
            callback()
        },
        style = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = PrimaryLightBlue
        )

    )
}