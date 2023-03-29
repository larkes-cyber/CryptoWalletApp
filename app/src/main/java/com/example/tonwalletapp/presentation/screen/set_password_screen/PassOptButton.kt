package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun PassOptButton(title:String, callback:() -> Unit) {
    Button(
        onClick = {
            callback()
        },
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(start = 20.dp),
        modifier = Modifier.height(48.dp).fillMaxWidth()
    ) {
        Text(
            text = title,
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
    }
}