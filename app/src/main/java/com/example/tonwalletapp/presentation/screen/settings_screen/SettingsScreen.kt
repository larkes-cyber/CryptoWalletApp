package com.example.tonwalletapp.presentation.screen.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.domain.mapper.toWordsString
import com.example.tonwalletapp.presentation.component.AlertDialogApp
import com.example.tonwalletapp.presentation.navigation.Screen
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.copyToClipboard

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsScreenViewModel
) {

    val showDeleteConfirmDialogUIState by viewModel.showDeleteConfirmDialogUIState.collectAsState()
    val wordsUIState by viewModel.showSecretWordsUIState.collectAsState()
    val secretWords by viewModel.secretWords.collectAsState()
    val walletDeletingHasConfirmedUIState by viewModel.walletDeletingHasConfirmedUIState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(walletDeletingHasConfirmedUIState){
        if(walletDeletingHasConfirmedUIState){
            navController.navigate(Screen.SplashScreen.route)
        }
    }

    Box() {
        if(wordsUIState) {
            AlertDialogApp(
                title = "Your secret words:",
                subtitle = secretWords.toWordsString(),
                firstOptionButtonTitle = "Copy",
                secondOptionButtonTitle = "Close",
                onFirstOptionButtonClick = {
                    context.copyToClipboard(secretWords.toWordsString())
                },
                onSecondOptionButtonClick = {
                    viewModel.hideWords()
                },
                onDismiss = {
                    viewModel.hideWords()
                }
            )
        }
        if(showDeleteConfirmDialogUIState){
            AlertDialogApp(
                title = "Are you sure?",
                subtitle = "You will delete wallet from your device",
                firstOptionButtonTitle = "No",
                secondOptionButtonTitle = "Yes",
                onFirstOptionButtonClick = {
                    viewModel.hideDeleteConfirmDialog()
                },
                onSecondOptionButtonClick = {
                    viewModel.deleteWallet()
                    viewModel.hideDeleteConfirmDialog()
                },
                onDismiss = {
                    viewModel.hideDeleteConfirmDialog()
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.secondBackground)
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = Constants.WALLET_SETTINGS_TITLE,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

            }
            Card(
                modifier = Modifier
                    .weight(6.4f)
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 25.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "General",
                        fontSize = 15.sp,
                        color = AppTheme.colors.primaryBackground,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    ClickableText(
                        text = AnnotatedString("Show recovery phrase"),
                        style = TextStyle( fontSize = 15.sp, color = AppTheme.colors.secondPrimaryTitle),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ){
                        viewModel.showSecretWords()
                    }
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
                        .height(1.dp))
                    ClickableText(
                        text = AnnotatedString("Change passcode"),
                        style = TextStyle( fontSize = 15.sp, color = AppTheme.colors.secondPrimaryTitle),
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ){

                    }
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
                        .height(1.dp))
                    ClickableText(
                        text = AnnotatedString("Delete Wallet"),
                        style = TextStyle( fontSize = 15.sp, color = AppTheme.colors.fourthPrimaryTitle),
                        modifier = Modifier.padding(horizontal = 20.dp),
                    ){
                        viewModel.showDeleteConfirmDialog()
                    }
                }
            }
        }
    }

}