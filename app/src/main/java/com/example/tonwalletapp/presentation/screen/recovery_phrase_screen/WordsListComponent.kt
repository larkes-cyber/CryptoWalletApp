package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.PrimaryLightGray
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun WordsListComponent(
    words:List<String>
) {
    val part1 = words.slice(0..11)
    val part2 = words.slice(11..22)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WordsColumnComponent(part1)
        WordsColumnComponent(part2, startIndex = 12)
    }

}

@Composable
fun WordsColumnComponent(
    list:List<String>,
    startIndex:Int = 0
){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        list.forEachIndexed {index, item ->
            WordComponent(
                index =startIndex + index + 1,
                word = item
            )
        }
    }
}

@Composable
fun WordComponent(
    word:String,
    index:Int
){
    Row() {
        Text(
            text = "${index}.  ",
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = PrimaryLightGray,
            textAlign = TextAlign.Center
        )
        Text(
            text = word,
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}