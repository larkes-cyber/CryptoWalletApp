package com.example.tonwalletapp.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeAgreeOption
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeSkipOption
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeText
import com.example.tonwalletapp.until.Constants.DidntHaveEnoughTimeTitle

@Composable
fun AlertDialogApp(
    title:String = "",
    subtitle:String = "",
    firstOptionButtonTitle:String = "",
    secondOptionButtonTitle:String = "",
    thirdOptionButtonTitle:String = "",
    onFirstOptionButtonClick:() -> Unit = {},
    onSecondOptionButtonClick:() -> Unit = {},
    onThirdOptionButtonClick:() -> Unit = {},
    onDismiss:() -> Unit = {}
) {

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = AppTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, start = 24.dp, bottom = 24.dp, end = 28.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp,
                    color = AppTheme.colors.primaryTitle
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = subtitle,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = AppTheme.colors.primaryTitle
                )
                Spacer(modifier = Modifier.height(28.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Row() {
                        if(firstOptionButtonTitle.isNotEmpty()) {
                            ClickableText(
                                text = AnnotatedString(firstOptionButtonTitle),
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = AppTheme.colors.primaryBackground
                                )
                            ) {
                                onFirstOptionButtonClick()
                            }
                        }
                        if(secondOptionButtonTitle.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(40.dp))
                            ClickableText(
                                text = AnnotatedString(secondOptionButtonTitle),
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = AppTheme.colors.primaryBackground
                                )
                            ) {
                                onSecondOptionButtonClick()
                            }
                        }
                        if(thirdOptionButtonTitle.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(40.dp))
                            ClickableText(
                                text = AnnotatedString(thirdOptionButtonTitle),
                                style = TextStyle(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    color = AppTheme.colors.primaryBackground
                                )
                            ) {
                                onThirdOptionButtonClick()
                            }
                        }
                    }
                }
            }
        }
    }

}