package com.example.tonwalletapp.presentation.screen.welcome_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.*
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.TitleColor
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize().padding(bottom = 58.dp), contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.spacedBy(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            item {
                SameHeaderComponent(
                    image = R.drawable.crystall,
                    title = "TON Wallet",
                    subtitle = "TON Wallet allows you to make fast and secure blockchain-based payments without intermediaries."
                )
            }

            item {
                ButtonComponent("Create my wallet"){
                    navController.navigate(Screen.CongratulationsScreen.route)
                }
                Spacer(modifier = Modifier.height(22.dp))
                TextButtonComponent("Import existing wallet"){

                }
            }

        }
    }

}