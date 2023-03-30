 package com.example.tonwalletapp.presentation.screen.set_password_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.ContentWrapperWithNavIconComponent
import com.example.tonwalletapp.presentation.component.KeyboardComponent
import com.example.tonwalletapp.presentation.component.SameHeaderComponent
import com.example.tonwalletapp.presentation.component.TextButtonComponent
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.theme.roboto

 @Composable
fun SetPasscodeScreen(
     navController: NavController,
     viewModel: SetPasscodeViewModel = hiltViewModel()
 ) {

     val showPassOptions by viewModel.showPassOptionsState
     val passLenState by viewModel.passLenState
     val pass by viewModel.passwordState
     val hasBeenFilled by viewModel.hasBeenFilled

     LaunchedEffect(hasBeenFilled){
         if(hasBeenFilled) {
             navController.navigate(Screen.ConfirmPassScreen.withArgs(pass))
             viewModel.resetScreen()
         }
     }

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
                            title = "Set a Passcode",
                            subtitle = "Enter the $passLenState digits in the passcode."
                        )
                    }
                    item {
                        if(showPassOptions){
                            Spacer(modifier = Modifier.height(6.dp))
                            Card(
                                modifier = Modifier.width(220.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Column() {
                                    PassOptButton(title = "4-digit code") {
                                        viewModel.setPassLen(4)
                                        viewModel.setShowPassOptionsState(false)
                                    }
                                    PassOptButton(title = "6-digit code") {
                                        viewModel.setPassLen(6)
                                        viewModel.setShowPassOptionsState(false)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                        }else{
                            Spacer(modifier = Modifier.height(41.dp))
                            ShowPassProgress(count = pass.length, passLen = passLenState)
                            Spacer(modifier = Modifier.height(44.dp))
                        }
                    }
                    item {
                        TextButtonComponent(text = "Passcode options") {
                            viewModel.setShowPassOptionsState(true)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(30.dp))

                        KeyboardComponent(){
                            if(it != "delete") viewModel.editPassword(pass + it)
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