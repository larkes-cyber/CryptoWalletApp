package com.example.tonwalletapp.presentation.screen.test_time_screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.view.AlertDialogApp
import com.example.tonwalletapp.presentation.view.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.view.PhraseWordTextField
import com.example.tonwalletapp.presentation.view.TopBarApp
import com.example.tonwalletapp.until.Constants.ContinueBtnText
import com.example.tonwalletapp.until.Constants.IncorrectWordErrorSeeWordsOption
import com.example.tonwalletapp.until.Constants.IncorrectWordErrorText
import com.example.tonwalletapp.until.Constants.IncorrectWordErrorTitle
import com.example.tonwalletapp.until.Constants.IncorrectWordErrorTryAgainOption
import com.example.tonwalletapp.until.Constants.TestTimeText
import com.example.tonwalletapp.until.Constants.TestTimeTitle

@Composable
fun TestTimeScreen(
    navController: NavController,
    viewModel: TestTimeViewModel
) {

    val testTimeUIState by viewModel.testTimeUIState.collectAsState()
    val hasBeenDoneUIState by viewModel.hasBeenDoneUIState.collectAsState()

    LaunchedEffect(hasBeenDoneUIState){
        if(hasBeenDoneUIState){
            navController.navigate(Screen.SuccessScreen.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if(testTimeUIState.activeIncorrectWordDialog){
            AlertDialogApp(
                title = IncorrectWordErrorTitle,
                subtitle = IncorrectWordErrorText,
                firstOptionButtonTitle = IncorrectWordErrorSeeWordsOption,
                onFirstOptionButtonClick = {
                    navController.navigate(Screen.RecoveryPhraseScreen.route)
                    viewModel.dismissDialog()
                },
                secondOptionButtonTitle = IncorrectWordErrorTryAgainOption,
                onSecondOptionButtonClick = {
                    viewModel.dismissDialog()
                },
                onDismiss = {
                    viewModel.dismissDialog()
                }
            )
        }

        TopBarApp {
            navController.popBackStack()
        }
        Box(modifier = Modifier.padding(horizontal = 40.dp)) {
            InfoScreenSkeleton(
                image = R.drawable.test_time_frame,
                imageSize = 80,
                title = TestTimeTitle,
                subtitle = TestTimeText + "${testTimeUIState.words[0].first}, ${testTimeUIState.words[1].first} and ${testTimeUIState.words[2].first}.",
                btnTitle = ContinueBtnText,
                visibleBtn = true,
                onBtnClick = {
                    viewModel.onDone()
                }
            ) {
                Spacer(modifier = Modifier.height(28.dp))
                Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                    PhraseWordTextField(rangePlace = testTimeUIState.words[0].first, word = testTimeUIState.firstWordTextFiled){
                        viewModel.onFirstWordChange(it)
                    }
                    PhraseWordTextField(rangePlace = testTimeUIState.words[1].first, word = testTimeUIState.secondWordTextFiled){
                        viewModel.onSecondWordChange(it)
                    }
                    PhraseWordTextField(rangePlace = testTimeUIState.words[2].first, word = testTimeUIState.thirdWordTextFiled){
                        viewModel.onThirdWordChange(it)
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
        }

    }

}