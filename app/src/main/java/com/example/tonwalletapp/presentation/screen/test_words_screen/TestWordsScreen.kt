package com.example.tonwalletapp.presentation.screen.test_words_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.component.*
import com.example.tonwalletapp.presentation.navigation.Screen

@Composable
fun TestWordsScreen(
    navController: NavController,
    viewModel: TestWordsViewModel = hiltViewModel(),
) {

    val checkNumbers = viewModel.numbers

    val wordState1 = viewModel.wordState1
    val wordState2 = viewModel.wordState2
    val wordState3 = viewModel.wordState3

    val status by viewModel.statusState

    LaunchedEffect(status.succeed){
        if(status.succeed) navController.navigate(Screen.PerfectScreen.route)
    }

    ContentWrapperWithNavIconComponent(
        content = {

            if(status.inCorrect){
                AlertDialogComponent(
                    title = "Incorrect words",
                    subtitle = "The secret words you have entered\n" +
                            "do not match the ones in the list.",
                    dismissTitle = "See words",
                    confirmTitle = "Try again",
                    onClose = {
                        viewModel.tryAgain()
                    },
                    onConfirm = {
                        viewModel.tryAgain()
                    },
                    onDismiss = {
                        viewModel.tryAgain()
                        navController.popBackStack()
                    }
                )
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
                                        "${checkNumbers!![0]}, ${checkNumbers[1]} and ${checkNumbers[2]}."
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
                                word = wordState1.value,
                                onChange = {
                                    wordState1.value = it
                                },
                                num = checkNumbers!![0]
                            )
                            TextFieldTestWordComponent(
                                word = wordState2.value,
                                onChange = {
                                    wordState2.value = it
                                },
                                num = checkNumbers[1]
                            )
                            TextFieldTestWordComponent(
                                word = wordState3.value,
                                onChange = {
                                    wordState3.value = it
                                },
                                num = checkNumbers[2]
                            )
                        }
                    }
                    item {
                        ButtonComponent(title = "Continue") {
                            viewModel.checkWords()
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