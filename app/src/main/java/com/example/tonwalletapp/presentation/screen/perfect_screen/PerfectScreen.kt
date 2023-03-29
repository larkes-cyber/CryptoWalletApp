package com.example.tonwalletapp.presentation.screen.perfect_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.*
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.inter

@Composable
fun PerfectScreen(
    navController: NavController
) {

    val checkboxState = remember {
        mutableStateOf(false)
    }

    ContentWrapperWithNavIconComponent(content = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SameHeaderComponent(
                            image = R.drawable.happy_sticker,
                            title = "Perfect!",
                            subtitle = "Now set up a passcode to secure\n" +
                                    "transactions."
                        )
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(42.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkboxState.value,
                            onCheckedChange = { checkboxState.value = it },
                            colors = CheckboxDefaults.colors(checkedColor = PrimaryLightBlue),
                            modifier = Modifier.absoluteOffset((-8).dp, 0.dp)
                        )
                        Text(
                            text = "Enable Biometric Auth",
                            fontWeight = FontWeight.Normal,
                            fontFamily = inter,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.absoluteOffset((-8).dp, 0.dp)
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    ButtonComponent(title = "Set a Passcode") {

                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }

    },
        callback = {  }
    )
}