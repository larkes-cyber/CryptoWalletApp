package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun PrimaryButtonApp(
    modifier: Modifier = Modifier,
    text:String = "",
    icon:Int? = null,
    onClick:() -> Unit
) {

    Button(
        modifier = modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.primaryBackground),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            onClick()
         },
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)){
                if(icon != null){
                    Icon(
                        painterResource(id = icon),
                        tint = AppTheme.colors.background,
                        contentDescription = "",
                        modifier = Modifier.size(10.dp)
                    )
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.button.copy(fontSize = 15.sp, color = AppTheme.colors.btnTitle)
                )
            }

        }
    }

}
