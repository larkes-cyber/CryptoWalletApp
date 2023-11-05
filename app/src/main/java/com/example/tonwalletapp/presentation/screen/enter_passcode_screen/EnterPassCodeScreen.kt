package com.example.tonwalletapp.presentation.screen.enter_passcode_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.DigitalKeyboard
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.component.PasswordProgressBar
import com.example.tonwalletapp.presentation.component.TopBarApp
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.screen.set_password_screen.PasswordUIState
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.SetPasswordText
import com.example.tonwalletapp.until.Constants.WRONG_PASSCODE_ERROR_TITLE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EnterPassCodeScreen(
    navController: NavController,
    passCode:String
) {
    var passwordUIState by remember {
        mutableStateOf("")
    }
    var wrongPassUIState by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    fun onNewPassSymbol(letter:String){
        wrongPassUIState = false
        coroutineScope.launch {
            passwordUIState += letter
            delay(200)
            if(passwordUIState.length >= 4){
                if(passwordUIState == passCode){
                    navController.navigate(Screen.MainWalletScreen.route)
                }else{
                    wrongPassUIState = true
                    passwordUIState = ""
                }
            }
        }
    }

    fun deleteLastChar(){
        if(passwordUIState.isNotEmpty()){
            val str = passwordUIState.dropLast(1)
            passwordUIState = str
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InfoScreenSkeleton(
                image = R.drawable.set_password_frame,
                title = SetPasswordText,
                subtitle = SetPasswordText,
                visibleBtn = false
            )
            Spacer(modifier = Modifier.height(40.dp))
            PasswordProgressBar(progress = passwordUIState.length)

            if(wrongPassUIState) {
                Box(modifier = Modifier.padding(top = 15.dp)) {
                    Text(
                        text = WRONG_PASSCODE_ERROR_TITLE,
                        fontSize = 15.sp,
                        color = AppTheme.colors.fourthPrimaryTitle
                    )
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
            Box(modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(bottom = 15.dp)) {
                DigitalKeyboard(
                    onBtnClick = {letter ->
                        onNewPassSymbol(letter.toString())
                    },
                    onDeleteBtnClick = {
                        deleteLastChar()
                    }
                )
            }
        }
    }


}