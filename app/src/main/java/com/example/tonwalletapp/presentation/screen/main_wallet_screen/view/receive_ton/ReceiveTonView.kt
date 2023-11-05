package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.receive_ton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.qr_code_untils.rememberQrBitmapPainter
import com.example.tonwalletapp.until.copyToClipboard

@Composable
fun ReceiveTonView(address:String) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp).padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = Constants.RECEIVE_TON_TITLE,
            fontSize = 20.sp,
            color = AppTheme.colors.secondBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Constants.RECEIVE_TON_SUBTITLE,
                fontSize = 15.sp,
                color = AppTheme.colors.strokeFormColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = rememberQrBitmapPainter(content = address),
                contentDescription = "",
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = address,
                fontSize = 15.sp,
                color = AppTheme.colors.secondBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(200.dp)
            )
        }
        PrimaryButtonApp(
            text = Constants.RECEIVE_TON_BTN_TITLE,
            modifier = Modifier.fillMaxWidth()
        ) {
            context.copyToClipboard(address)
        }

    }

}