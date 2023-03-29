package com.example.tonwalletapp.presentation.screen.all_set_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.ButtonComponent
import com.example.tonwalletapp.presentation.component.ContentWrapperWithNavIconComponent
import com.example.tonwalletapp.presentation.component.SameHeaderComponent

@Composable
fun AllSetScreen(
    navController: NavController
) {
    ContentWrapperWithNavIconComponent(
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(106.dp)
                ){
                    item {
                      SameHeaderComponent(image = R.drawable.happy_sticker, title = "Ready to go!", subtitle = "You are all set. Now you have a wallet that\n" +
                              "only you control — directly, without\n" +
                              "middlemen or bankers. ")
                    }
                    item { 
                        ButtonComponent(title = "View my wallet") {
                            
                        }
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                    
                }
            }
        },
        callback = {
            navController.popBackStack()
        }
    )
}