package com.example.tonwalletapp.presentation.screen.confirm_pass_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.ContentWrapperWithNavIconComponent
import com.example.tonwalletapp.presentation.component.KeyboardComponent
import com.example.tonwalletapp.presentation.component.SameHeaderComponent
import com.example.tonwalletapp.presentation.component.TextButtonComponent
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.screen.set_password_screen.PassOptButton
import com.example.tonwalletapp.presentation.screen.set_password_screen.ShowPassProgress

@Composable
fun ConfirmPassScreen(
    navController: NavController,
    password: String,
    viewModel: ConfirmPassViewModel = hiltViewModel()
) {

    val passState by viewModel.passwordState
    val hasBeenConfirmed by viewModel.hasBeenConfirmed


    LaunchedEffect(hasBeenConfirmed){
        if(hasBeenConfirmed) navController.navigate(Screen.AllSetScreen.route)
    }

    Log.d("sdfgdsgsd",password)

    ContentWrapperWithNavIconComponent(
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        SameHeaderComponent(
                            image = R.drawable.monkey_sticker,
                            title = "Confirm a Passcode   ",
                            subtitle = "Enter the ${password.length} digits in the passcode."
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(41.dp))
                        ShowPassProgress(count = passState.length, passLen = password.length)
                        Spacer(modifier = Modifier.height(44.dp))
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))

                        KeyboardComponent(){
                            if(it != "delete") viewModel.editPassword(passState + it, password)
                            else viewModel.removeLastSymbol()
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        },
        callback = {
            navController.popBackStack()
        }
    )

}