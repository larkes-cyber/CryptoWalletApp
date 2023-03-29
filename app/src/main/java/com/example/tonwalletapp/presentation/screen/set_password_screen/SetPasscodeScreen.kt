package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.ContentWrapperWithNavIconComponent
import com.example.tonwalletapp.presentation.component.SameHeaderComponent

@Composable
fun SetPasscodeScreen(
    navController: NavController
) {
    
    ContentWrapperWithNavIconComponent(
        content = {
                  LazyColumn(
                      modifier = Modifier.fillMaxSize()
                  ){
                      item {
                          SameHeaderComponent(
                              image = R.drawable.monkey_sticker,
                              title = "Set a Passcode",
                              subtitle = "Enter the 4 digits in the passcode."
                          )
                      }
                  }
        },
        callback = { }
    )

}