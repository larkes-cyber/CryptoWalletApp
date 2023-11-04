package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.txt_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.domain.mapper.toFormattedAddress
import com.example.tonwalletapp.domain.mapper.toRoundAmount
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.component.TxtMessage
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view.TxtDetailItem
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.IN_TRANSACTION
import com.example.tonwalletapp.until.Constants.SEND_TON_BTN_TITLE
import com.example.tonwalletapp.until.Constants.TRANSACTION_DETAIL_TITLE
import com.example.tonwalletapp.until.Constants.VIEW_IN_EXPLORER_BTN_TITLE
import com.example.tonwalletapp.until.copyToClipboard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionDetailView(
    transactionDetail: TransactionDetail
) {

    val context = LocalContext.current

    Column(
    ) {
        Text(
            text = TRANSACTION_DETAIL_TITLE,
            fontSize = 20.sp,
            color = AppTheme.colors.secondBackground,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.ton_crystal_frame), contentDescription = "", modifier = Modifier.size(33.dp))
                Text(
                    text = transactionDetail.amount.toRoundAmount().toString(),
                    fontSize = 35.sp,
                    color = if(transactionDetail.transactionType == IN_TRANSACTION) AppTheme.colors.thirdPrimaryTitle else AppTheme.colors.fourthPrimaryTitle,
                    fontWeight = FontWeight.Medium
                )
            }
            if(transactionDetail.transactionType != IN_TRANSACTION) {
                Text(
                    text = "${transactionDetail.fee} transaction fee",
                    fontSize = 15.sp,
                    color = AppTheme.colors.strokeFormColor
                )
            }
            Text(
                text = transactionDetail.time,
                fontSize = 15.sp,
                color = AppTheme.colors.strokeFormColor
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TxtMessage(transactionDetail.message)
        }
        Column(modifier = Modifier.padding(top = 32.dp)) {
            Text(
                text = "Details",
                fontSize = 15.sp,
                color = AppTheme.colors.primaryBackground,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 20.dp)
            )
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                TxtDetailItem(
                    firstComponent = {
                        Text(
                            text =if(transactionDetail.transactionType == IN_TRANSACTION) "Sender address" else "Recipient address",
                            fontSize = 15.sp,
                            color = AppTheme.colors.primaryTitle,
                        )
                    },
                    secondComponent = {
                        Text(
                            text = (if(transactionDetail.transactionType == IN_TRANSACTION) transactionDetail.senderAddr!! else transactionDetail.recipientAddr!!).toFormattedAddress(),
                            fontSize = 15.sp,
                            color = AppTheme.colors.primaryTitle,
                            modifier = Modifier.combinedClickable(
                                onClick = { },
                                onLongClick = {
                                    context.copyToClipboard(if(transactionDetail.transactionType == IN_TRANSACTION) transactionDetail.senderAddr!! else transactionDetail.recipientAddr!!)
                                }
                            )
                        )
                    }
                )
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
                .height(1.dp))
            Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                TxtDetailItem(
                    firstComponent = {
                        Text(
                            text = "Transaction",
                            fontSize = 15.sp,
                            color = AppTheme.colors.primaryTitle
                        )
                    },
                    secondComponent = {
                        Text(
                            text = transactionDetail.txtAddress.toFormattedAddress(),
                            fontSize = 15.sp,
                            color = AppTheme.colors.primaryTitle
                        )
                    }
                )
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.btnSubtitle.copy(alpha = 0.7f))
                .height(1.dp))
        }

        ClickableText(
            text = AnnotatedString(VIEW_IN_EXPLORER_BTN_TITLE),
            onClick = {},
            style = TextStyle(
                fontSize = 15.sp,
                color = AppTheme.colors.primaryBackground,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(start = 20.dp, top = 14.dp)
        )
        Spacer(modifier = Modifier.height(38.dp))
        Box(modifier = Modifier.padding(horizontal = 16.dp)){
            PrimaryButtonApp(
                text = SEND_TON_BTN_TITLE,
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }

    }

}