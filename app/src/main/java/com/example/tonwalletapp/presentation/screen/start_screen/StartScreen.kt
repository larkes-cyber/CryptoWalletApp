package com.example.tonwalletapp.presentation.screen.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.view.PrimaryButtonApp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.CreateWalletBtnText
import com.example.tonwalletapp.until.Constants.ImportWalletBtnText
import com.example.tonwalletapp.until.Constants.TonWalletText
import com.example.tonwalletapp.until.Constants.TonWalletTitle

@Composable
fun StartScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ton_crystal_frame),
            contentDescription = "",
            modifier = Modifier.size(95.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = TonWalletTitle,
            style = MaterialTheme.typography.h1.copy(fontSize = 24.sp),
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = TonWalletText,
            style = MaterialTheme.typography.body1.copy(fontSize = 15.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(100.dp))
        PrimaryButtonApp(
            modifier = Modifier.width(200.dp),
            text = CreateWalletBtnText
        ) {
        }
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(
            text = AnnotatedString(ImportWalletBtnText),
            onClick = {},
            style = MaterialTheme.typography.button.copy(color = AppTheme.colors.primaryBackground)
        )


    }

}