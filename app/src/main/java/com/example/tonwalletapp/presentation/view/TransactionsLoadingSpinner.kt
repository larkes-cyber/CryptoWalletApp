package com.example.tonwalletapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tonwalletapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TransactionsLoadingSpinner(
    tonIconModifier: Modifier = Modifier,
    dollarIconModifier:Modifier = Modifier,
    arrowIconModifier:Modifier = Modifier
) {
    val coroutineScope= rememberCoroutineScope()
    val phase = remember { mutableStateOf(1) }
    LaunchedEffect(Unit){
        coroutineScope.launch {
            while(true) {
                phase.value += 1
                if(phase.value == 5) phase.value = 1
                delay(150)
            }
        }
    }


    Row() {
        Column() {
            when(phase.value){
                1 -> IconFrame(R.drawable.dollar, dollarIconModifier)
                2 -> TonArrow(30f, arrowIconModifier)
                3 -> IconFrame(R.drawable.ton_icon, tonIconModifier)
                4 -> TonArrow(30f, arrowIconModifier)
            }
            Spacer(modifier = Modifier.height(3.dp))
            when(phase.value){
                1 -> TonArrow(-30f, arrowIconModifier)
                2 -> IconFrame(R.drawable.ton_icon, tonIconModifier)
                3 -> TonArrow(-30f, arrowIconModifier)
                4 -> IconFrame(R.drawable.dollar, dollarIconModifier)
            }
        }
        Column() {
            when (phase.value) {
                1 -> TonArrow(150f, arrowIconModifier)
                2 -> IconFrame(R.drawable.dollar, dollarIconModifier)
                3 -> TonArrow(150f, arrowIconModifier)
                4 -> IconFrame(R.drawable.ton_icon, tonIconModifier)
            }
            Spacer(modifier = Modifier.height(3.dp))
            when(phase.value){
                1 -> IconFrame(R.drawable.ton_icon, tonIconModifier)
                2 -> TonArrow(210f, arrowIconModifier)
                3 -> IconFrame(R.drawable.dollar, dollarIconModifier)
                4 -> TonArrow(210f, arrowIconModifier)
            }
        }
    }

}



@Composable
fun IconFrame(resource:Int, modifier: Modifier){
    Image(
        painter = painterResource(id = resource),
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )

}

@Composable
fun TonArrow(rotate:Float, modifier: Modifier){

    Image(
        painter = painterResource(id = R.drawable.ton_arrow_icon),
        contentDescription = "",
        modifier = modifier
            .rotate(rotate),
        contentScale = ContentScale.Crop
    )

}