package com.example.tonwalletapp.presentation.screen.congratulations_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.ButtonComponent
import com.example.tonwalletapp.presentation.component.ContentWrapperWithNavIconComponent
import com.example.tonwalletapp.presentation.component.SubtitleComponent
import com.example.tonwalletapp.presentation.component.TitleComponent

@Composable
fun CongratulationsScreen(
    navController: NavController
) {

    ContentWrapperWithNavIconComponent(
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(bottom = 100.dp), contentAlignment = Alignment.BottomCenter) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(86.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Image(
                                painterResource(id = R.drawable.congrat_img),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(100.dp)
                            )
                            TitleComponent("Congratulations")
                            SubtitleComponent("Your TON Wallet has just been created. \n" +
                                    "Only you control it.")
                            Spacer(modifier = Modifier.height(1.dp))
                            SubtitleComponent("To be able to always have access to it, please write down secret words and set up a secure passcode.")
                        }
                    }
                    item {
                        ButtonComponent(title = "Proceed") {

                        }
                    }
                }
            }
        },
        callback = {

        }
    )

    
}