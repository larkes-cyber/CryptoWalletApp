package com.example.tonwalletapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.roboto
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun RecoveryPhraseTable(
    words:List<String>
) {
    val firstColumn = words.slice(0..9)
    val secondColumn = words.slice(10..19)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            firstColumn.forEachIndexed { index, item ->
                Text(
                    text = "${index + 1}. $item",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.primaryTitle,
                    fontFamily = roboto
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            secondColumn.forEachIndexed { index, item ->
                Text(
                    text = "${index + 14}. $item",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.primaryTitle,
                    fontFamily = roboto
                )
            }
        }

    }
}