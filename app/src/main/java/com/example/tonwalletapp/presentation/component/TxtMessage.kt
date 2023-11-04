package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun TxtMessage(msg:String?) {
    Card(
        shape = RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp
        ),
        elevation = 0.dp,
        backgroundColor = AppTheme.colors.secondFormColor
    ) {
        Box(modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)) {
            Text(
                text = msg ?: "None message",
                color = AppTheme.colors.secondBackground,
                fontSize = 15.sp
            )
        }
    }
}