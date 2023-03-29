package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.presentation.theme.KeyboardButtonsColor
import com.example.tonwalletapp.presentation.theme.roboto
import com.example.tonwalletapp.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KeyboardComponent(
    callback:(String) -> Unit
) {

    val symbols = listOf(
        Pair("1", listOf()),
        Pair("2", listOf("A","B","C")),
        Pair("3", listOf("D","E","F")),
        Pair("4", listOf("G","H","I")),
        Pair("5", listOf("J","K","L")),
        Pair("6", listOf("M","N","O")),
        Pair("7", listOf("P","Q","R","S")),
        Pair("8", listOf("T","U","V")),
        Pair("9", listOf("W","X","Y","Z")),
        Pair("0", listOf("+")),
        Pair("delete", listOf("")),
    )

    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 109.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(223.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ){
        
        itemsIndexed(symbols){_, item ->
            Button(
                onClick = {
                    callback(item.first)
                },
                modifier = Modifier.height(47.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = KeyboardButtonsColor),
                shape = RoundedCornerShape(6.dp),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                if(item.first == "delete"){
                    Icon(
                        painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "",
                        modifier = Modifier.width(20.dp).height(15.dp),
                        tint = Color(0xFF3D3E40)
                    )
                }else{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.first,
                                fontFamily = roboto,
                                fontWeight = FontWeight.Normal,
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = item.second.joinToString(""),
                                fontFamily = roboto,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                                color = Color(0xFFA8A8A8)
                            )
                        }
                    }
                }
            }
        }

    }



}