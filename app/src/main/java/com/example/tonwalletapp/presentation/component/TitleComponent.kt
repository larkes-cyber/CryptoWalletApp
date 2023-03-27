package com.example.tonwalletapp.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.TitleColor
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun TitleComponent(
    text:String
) {
    Text(
        text = text,
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = TitleColor
    )
}