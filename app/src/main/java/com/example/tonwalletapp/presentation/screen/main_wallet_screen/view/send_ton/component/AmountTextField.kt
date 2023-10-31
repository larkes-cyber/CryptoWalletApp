package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.ui.theme.AppTheme

@Composable
fun AmountTextField(
    amount:String,
    exception: String = ""
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ton_crystal_frame),
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            if(amount.isEmpty()){
                AnimatedFiledStick()
                Text(
                    text = "0",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.strokeFormColor
                )
            }else{
                Text(
                    text = if(amount.length > 6) amount.substring(0..5) else amount,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Medium,
                    color = if(exception.isNotEmpty()) AppTheme.colors.fourthPrimaryTitle else AppTheme.colors.primaryTitle
                )
                AnimatedFiledStick()
            }

        }
        if(exception.isNotEmpty()) {
            Text(
                text = exception,
                fontSize = 16.sp,
                color = AppTheme.colors.fourthPrimaryTitle,
                fontWeight = FontWeight.Medium
            )
        }
    }
  


}

@Composable
fun AnimatedFiledStick(){

    val transition = rememberInfiniteTransition()
    val opacity by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        )
    )

    Divider(
        modifier = Modifier
            .alpha(opacity)
            .height(38.dp)
            .width(2.dp)
            .background(AppTheme.colors.primaryBackground)
    )
}