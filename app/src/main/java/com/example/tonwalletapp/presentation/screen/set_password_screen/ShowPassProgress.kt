package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShowPassProgress(
    count:Int,
    passLen:Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for(i in 1..passLen){
            PassCircle(filled = i <= count)
        }
    }
}

@Composable
fun PassCircle(filled:Boolean){
    Card(
        shape = RoundedCornerShape(100),
        modifier = Modifier.size(18.dp),
        border = BorderStroke(1.dp, if(filled) Color.Black else Color(0xFFDBDBDB)),
        elevation = 0.dp,
        backgroundColor = if(filled) Color.Black else Color.White
    ) {

    }
}