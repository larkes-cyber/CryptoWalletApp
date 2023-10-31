package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.R

@Composable
fun DigitalKeyboard(
    modifier: Modifier = Modifier,
    onDotClick:() -> Unit = {},
    onBtnClick:(Int) -> Unit = {},
    onDeleteBtnClick:() -> Unit = {},
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            (1..3).forEach {
                DigitalKeyboardBtn(
                    modifier = Modifier.weight(1f),
                    digit = it.toString(),
                ){
                    onBtnClick(it)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            (4..6).forEach {
                DigitalKeyboardBtn(
                    modifier = Modifier.weight(1f),
                    digit = it.toString(),
                ){
                    onBtnClick(it)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            (7..9).forEach {
                DigitalKeyboardBtn(
                    modifier = Modifier.weight(1f),
                    digit = it.toString(),
                ){
                    onBtnClick(it)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            DigitalKeyboardBtn(
                modifier = Modifier.weight(1f),
                digit = ".",
            ){
                onDotClick()
            }
            DigitalKeyboardBtn(
                modifier = Modifier.weight(1f),
                digit = "0",
            ){
                onBtnClick(0)
            }
            Button(
                modifier = Modifier.weight(1f).height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonColor),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(0.dp),
                elevation = ButtonDefaults.elevation(0.dp),
                onClick = {
                    onDeleteBtnClick()
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Icon(painterResource(
                        id = R.drawable.delete_keyboard_btn),
                        contentDescription = "",
                        tint = AppTheme.colors.primaryTitle,
                        modifier = Modifier.width(20.dp).height(15.dp)
                    )
                }
            }

        }
    }

}

@Composable
fun DigitalKeyboardBtn(
    modifier: Modifier = Modifier,
    digit:String,
    onClick:() -> Unit
) {

    Button(
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.buttonColor),
        shape = RoundedCornerShape(6.dp),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        ){
            Text(
                text = digit,
                fontSize = 24.sp,
                color = AppTheme.colors.primaryTitle
            )
        }
    }


}