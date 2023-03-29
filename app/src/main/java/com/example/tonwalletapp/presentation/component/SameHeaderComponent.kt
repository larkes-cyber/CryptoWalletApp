package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tonwalletapp.R

@Composable
fun SameHeaderComponent(
    image:Int,
    title:String,
    subtitle:String
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
        TitleComponent(title)
        SubtitleComponent(subtitle)
    }

}