package com.example.tonwalletapp.presentation.screen.test_words_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.*
import com.example.tonwalletapp.presentation.navigation.Screen

@Composable
fun TestWordsScreen(
    navController: NavController
) {

    val wordState = remember{
        mutableStateOf("")
    }

    val showAlertDialog = remember {
        mutableStateOf(true)
    }

    ContentWrapperWithNavIconComponent(
        content = {

            if(showAlertDialog.value){
                AlertDialogComponent(
                    title = "Incorrect words",
                    subtitle = "The secret words you have entered\n" +
                            "do not match the ones in the list.",
                    dismissTitle = "See words",
                    confirmTitle = "Try again",
                    onClose = {
                        showAlertDialog.value = false
                    })
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            SameHeaderComponent(
                                image = R.drawable.teacher,
                                title = "Test Time!",
                                subtitle = "Let’s check that you wrote them down correctly. Please enter the words \n" +
                                        "5, 15 and 18."
                            )
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(17.dp)
                        ) {
                            TextFieldTestWordComponent(
                                word = wordState.value,
                                onChange = {
                                    wordState.value = it
                                },
                                num = 5
                            )
                            TextFieldTestWordComponent(
                                word = wordState.value,
                                onChange = {
                                    wordState.value = it
                                },
                                num = 5
                            )
                            TextFieldTestWordComponent(
                                word = wordState.value,
                                onChange = {
                                    wordState.value = it
                                },
                                num = 5
                            )
                        }
                    }
                    item {
                        ButtonComponent(title = "Continue") {
                            navController.navigate(Screen.PerfectScreen.route)
                        }
                        Spacer(modifier = Modifier.height(92.dp))
                    }
                }
            }
        },callback = {
            navController.popBackStack()
        }
    )

}