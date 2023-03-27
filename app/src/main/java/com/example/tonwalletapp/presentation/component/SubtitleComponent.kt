package com.example.tonwalletapp.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun SubtitleComponent(
    title:String
) {
    Text(
        text = title,
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}