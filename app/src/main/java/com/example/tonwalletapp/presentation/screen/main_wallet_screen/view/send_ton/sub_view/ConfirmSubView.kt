package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.domain.mapper.toFormattedAddress
import com.example.tonwalletapp.domain.mapper.toRoundAmount
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.component.TonPrimaryTextField
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.COMMENT_DESCR
import com.example.tonwalletapp.until.Constants.COMMENT_TITLE
import com.example.tonwalletapp.until.Constants.DETAILS_TITLE
import com.example.tonwalletapp.until.Constants.DONT_HAVE_TON
import com.example.tonwalletapp.until.Constants.PAYMENT_DESCR
import kotlin.math.roundToInt

@Composable
fun ConfirmSubView(
    receiverAddr:String,
    amount:Float,
    fee:Float,
    walletBalance:Float,
    onDone:(String) -> Unit
) {

    val commentUIState = remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(
                text = COMMENT_TITLE,
                fontSize = 15.sp,
                color = AppTheme.colors.primaryBackground,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(12.dp))
            TonPrimaryTextField(
                placeholder = PAYMENT_DESCR,
                text = commentUIState.value,
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                commentUIState.value = it
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = COMMENT_DESCR,
                fontSize = 13.sp,
                color = AppTheme.colors.strokeFormColor
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = DETAILS_TITLE,
                fontSize = 15.sp,
                color = AppTheme.colors.primaryBackground,
                fontWeight = FontWeight.Medium
            )
            TxtDetail(amount = amount, addr = receiverAddr, fee = fee, maBalance = walletBalance)
            if(amount - fee > walletBalance){
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = DONT_HAVE_TON,
                    fontSize = 14.sp,
                    color = AppTheme.colors.fourthPrimaryTitle,
                    fontWeight = FontWeight.Medium
                )
            }

        }
        PrimaryButtonApp(text = Constants.CONFIRM_AND_SEND_BTN_TITLE, modifier = Modifier.fillMaxWidth()) {
            onDone(commentUIState.value)
        }
    }

}

@Composable
fun TxtDetail(
    addr:String,
    amount: Float,
    fee:Float,
    maBalance:Float
){
    TxtDetailItem(
        firstComponent = {
            Text(
                text = "Recipient",
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        },
        secondComponent = {
            Text(
                text = addr.toFormattedAddress(),
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        }
    )
    Divider(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
        .height(1.dp))
    TxtDetailItem(
        firstComponent = {
            Text(
                text = "Amount",
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        },
        secondComponent = {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ton_crystal_frame), contentDescription = "", modifier = Modifier.size(15.dp))
                Text(
                    text = (if(amount + fee > maBalance) amount - fee else amount + fee).toRoundAmount().toString(),
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                )
            }
        }
    )
    Divider(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
        .height(1.dp))
    TxtDetailItem(
        firstComponent = {
            Text(
                text = "Fee",
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        },
        secondComponent = {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ton_crystal_frame), contentDescription = "", modifier = Modifier.size(15.dp))
                Text(
                    text = "≈ ${String.format("%.2f", fee)}",
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                )
            }
        }
    )
    Divider(modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
        .height(1.dp))

    TxtDetailItem(
        firstComponent = {
            Text(
                text = "Total",
                fontSize = 15.sp,
                color = AppTheme.colors.primaryTitle
            )
        },
        secondComponent = {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ton_crystal_frame), contentDescription = "", modifier = Modifier.size(15.dp))
                Text(
                    text = "≈ " + ((if(amount + fee > maBalance) amount - fee else amount + fee) + fee).toRoundAmount().toString(),
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                )
            }
        }
    )
}

@Composable
fun TxtDetailItem(
    firstComponent:@Composable()() -> Unit,
    secondComponent:@Composable()() -> Unit
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        firstComponent()
        secondComponent()
    }
}

//DETAILS_TITLE