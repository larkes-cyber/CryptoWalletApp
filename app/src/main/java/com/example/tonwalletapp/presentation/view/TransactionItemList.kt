package com.example.tonwalletapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.IN_TRANSACTION

@Composable
fun TransactionItemList(
    transactionDetail: TransactionDetail
) {

    fun formatAddress(address:String):String{
        return "${address.take(6)}...${address.takeLast(7)}"
    }

    Column() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ton_crystal_frame),
                        contentDescription = "",
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = transactionDetail.amount.toString(),
                        color = if(transactionDetail.transactionType == IN_TRANSACTION) AppTheme.colors.thirdPrimaryTitle else AppTheme.colors.fourthPrimaryTitle,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = if(transactionDetail.transactionType == IN_TRANSACTION) "from" else "to",
                        color = AppTheme.colors.strokeFormColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Text(
                    text = formatAddress(if (transactionDetail.transactionType == IN_TRANSACTION) transactionDetail.senderAddr!! else transactionDetail.recipientAddr!!),
                    color = AppTheme.colors.secondBackground,
                    fontSize = 14.sp
                )
                if(transactionDetail.transactionType != IN_TRANSACTION) {
                    Text(
                        text = "-${transactionDetail.fee} fee",
                        color = AppTheme.colors.strokeFormColor,
                        fontSize = 14.sp
                    )
                }
                Card(
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    ),
                    elevation = 0.dp,
                    backgroundColor = AppTheme.colors.secondFormColor
                ) {
                    Box(modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp)) {
                        Text(
                            text = transactionDetail.message ?: "None message",
                            color = AppTheme.colors.secondBackground,
                            fontSize = 15.sp
                        )
                    }
                }

            }
            Text(
                text = transactionDetail.time,
                color = AppTheme.colors.strokeFormColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

