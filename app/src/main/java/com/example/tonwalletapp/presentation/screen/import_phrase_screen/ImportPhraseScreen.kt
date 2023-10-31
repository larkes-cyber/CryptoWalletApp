package com.example.tonwalletapp.presentation.screen.import_phrase_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.presentation.component.AlertDialogApp
import com.example.tonwalletapp.presentation.component.InfoScreenSkeleton
import com.example.tonwalletapp.presentation.component.PhraseWordTextField
import com.example.tonwalletapp.presentation.component.TopBarApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.ContinueBtnTitle
import com.example.tonwalletapp.until.Constants.DontHavePhraseBtnTitle
import com.example.tonwalletapp.until.Constants.ImportPhraseText
import com.example.tonwalletapp.until.Constants.ImportPhraseTitle

@Composable
fun ImportPhraseScreen(
    navController: NavController,
    viewModel: ImportPhraseViewModel
) {

    val phraseUIState by viewModel.phraseUIState.collectAsState()
    val currentFocusPositionUIState by viewModel.currentFocusPositionUIState.collectAsState()
    val walletUIState by viewModel.walletUIState.collectAsState()


    LaunchedEffect(walletUIState){
        if(walletUIState.success){
            navController.navigate(Screen.ImportSuccessScreen.route)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colors.background)) {
        if(walletUIState.error.isNotEmpty()){
            AlertDialogApp(
                title = Constants.IncorrectWordErrorTitle,
                subtitle = Constants.IncorrectImportedPhraseText,
                firstOptionButtonTitle = Constants.OkBtnText,
                onFirstOptionButtonClick = {
                    viewModel.resetWalletState()
                },
                onDismiss = {
                    viewModel.resetWalletState()
                }
            )
        }
        if(walletUIState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if(!walletUIState.isLoading && walletUIState.error.isEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 72.dp)
                    .padding(horizontal = 40.dp)
            ) {
                InfoScreenSkeleton(
                    title = ImportPhraseTitle,
                    subtitle = ImportPhraseText,
                    btnTitle = ContinueBtnTitle,
                    error = walletUIState.error,
                    visibleBtn = true,
                    onBtnClick = {
                        viewModel.onFinish()
                    },
                    image = R.drawable.recovery_phrase_frame
                ){
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        ClickableText(
                            modifier = Modifier.padding(top = 12.dp, bottom = 3.dp),
                            text = AnnotatedString(DontHavePhraseBtnTitle),
                            style = MaterialTheme.typography.button.copy(color = AppTheme.colors.primaryBackground),
                            onClick = {
                                navController.navigate(Screen.WrongPhraseScreen.route)
                            }
                        )
                        (1..24).forEach{position ->
                            Box(modifier = Modifier.padding(top = 17.dp)) {
                                PhraseWordTextField(
                                    focused = currentFocusPositionUIState == position,
                                    word = phraseUIState[position-1],
                                    rangePlace = position,
                                    onWordChange = {
                                        viewModel.onPhraseChange(position = position, word = it)
                                    },
                                    onNextClick = {
                                        viewModel.onCurrentPosition(position + 1)
                                    },
                                    onWrongValueChange = {
                                        viewModel.onWrongValueError(
                                            num = position,
                                            bool = it
                                        )
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
        TopBarApp() {
            navController.popBackStack()
        }
    }

}