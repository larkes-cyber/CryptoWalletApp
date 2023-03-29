package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowPassProgress(
    count:Int
) {

}

@Composable
fun PassCircle(filled:Boolean){
    Card(
        shape = RoundedCornerShape(100),
        modifier = Modifier.size(16.dp),
        border = BorderStroke(1.dp, Color(0xFFDBDBDB)),
        elevation = 0.dp
    ) {

    }
}