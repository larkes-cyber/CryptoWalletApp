package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun PasswordProgressBar(
    progress:Int
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        (1..4).forEach {
            Card(
                modifier = Modifier.size(16.dp),
                shape = RoundedCornerShape(100),
                backgroundColor =if(it <= progress) AppTheme.colors.primaryTitle else Color.Transparent,
                elevation = 0.dp,
                border = BorderStroke(if(it > progress) 1.dp else 0.dp, color = AppTheme.colors.strokeFormColor)
            ) {

            }
        }
    }

}
