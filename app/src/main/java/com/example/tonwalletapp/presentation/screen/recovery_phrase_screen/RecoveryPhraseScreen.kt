package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.*
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun RecoveryPhraseScreen(
    navController: NavController,
    viewModel: RecoveryPhraseViewModel = hiltViewModel()
) {

    val showAlertDialog = remember {
        mutableStateOf(false)
    }

    ContentWrapperWithNavIconComponent(
        content = {

            if(showAlertDialog.value){
                AlertDialogComponent(
                    title = "Sure done?",
                    subtitle = "You didn’t have enough time to\n" +
                            "write these words down.",
                    dismissTitle = "Skip",
                    confirmTitle = "OK, sorry",
                    onClose = {
                        showAlertDialog.value = false
                    },
                    onConfirm = {
                        navController.navigate(Screen.TestWordsScreen.route)
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ){

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SameHeaderComponent(
                            image = R.drawable.list_img,
                            title = "Your Recovery Phrase",
                            subtitle = "Write down these 24 words in this exact order and keep them in a secure place. Do not share this list with anyone. If you lose it, you will irrevocably lose access to your TON account."
                        )
                    }
                }
                
                item { 
                    WordsListComponent(words = (0..22).map { "network" })
                }

                item { 
                    Spacer(modifier = Modifier.height(4.dp))
                    ButtonComponent(title = "Done") {
                        navController.navigate(Screen.TestWordsScreen.route)
                    }
                    Spacer(modifier = Modifier.height(56.dp))
                }

            }
        },
        title = {
            Text(
                text = "Your Recovery Phrase",
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        callback = {
            navController.popBackStack()
        },
        elevation = 3
    )

}