package com.example.tonwalletapp.presentation.screen.recovery_phrase_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.component.AlertDialogApp
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.component.RecoveryPhraseTable
import com.example.tonwalletapp.presentation.component.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeAgreeOption
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeSkipOption
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeText
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeTitle
import com.example.tonwalletapp.until.Constants.DoneBtnText
import com.example.tonwalletapp.until.Constants.TonRecoveryPhraseText
import com.example.tonwalletapp.until.Constants.TonRecoveryPhraseTitle

@Composable
fun RecoveryPhraseScreen(
    navController: NavController,
    viewModel: RecoveryPhraseViewModel
) {

    val phraseWordsUIState by viewModel.phraseWordsUIState.collectAsState()
    val activeTimeAlertDialogUIState by viewModel.activeTimeAlertDialogUIState.collectAsState()
    val hasBeenDoneUIState by viewModel.hasBeenDoneUIState.collectAsState()

    LaunchedEffect(hasBeenDoneUIState){
        if(hasBeenDoneUIState){
            navController.navigate(Screen.TestTimeScreen.route)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        if(activeTimeAlertDialogUIState){
            AlertDialogApp(
                title = DidntHaveEnoughTimeTitle,
                subtitle = DidntHaveEnoughTimeText,
                firstOptionButtonTitle = DidntHaveEnoughTimeSkipOption,
                onFirstOptionButtonClick = {
                    viewModel.dismissDialog()
                    navController.navigate(Screen.TestTimeScreen.route)
                },
                secondOptionButtonTitle = DidntHaveEnoughTimeAgreeOption,
                onSecondOptionButtonClick = {
                    viewModel.dismissDialog()
                },
                onDismiss = {
                    viewModel.dismissDialog()
                }
            )
        }
        Column(modifier = Modifier
            .padding(horizontal = 40.dp)
            .padding(top = 68.dp)
        ) {
            InfoScreenSkeleton(
                image = R.drawable.recovery_phrase_frame,
                title = TonRecoveryPhraseTitle,
                imageSize = 80,
                subtitle = TonRecoveryPhraseText,
                btnTitle = DoneBtnText,
                visibleBtn = true,
                onBtnClick = {
                       viewModel.onDone()
                },
                onOptionBtnClick = {

                }
            ){
                Spacer(modifier = Modifier.height(40.dp))
                if(phraseWordsUIState.isLoading){
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                if(phraseWordsUIState.words.isNotEmpty()) RecoveryPhraseTable(words = phraseWordsUIState.words)
                Spacer(modifier = Modifier.height(44.dp))
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
        TopBarApp {
            navController.popBackStack()
        }
    }

}