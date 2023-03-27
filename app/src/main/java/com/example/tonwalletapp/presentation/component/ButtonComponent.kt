package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun ButtonComponent(
    title:String,
    callback:() -> Unit
) {

    Button(
        onClick = { callback() },
        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryLightBlue),
        modifier = Modifier
            .width(220.dp)
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(vertical = 4.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.White
            )
        }

    }

}