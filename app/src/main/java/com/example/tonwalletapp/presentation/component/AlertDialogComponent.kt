package com.example.tonwalletapp.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.tonwalletapp.presentation.theme.PrimaryLightBlue
import com.example.tonwalletapp.presentation.theme.TitleColor
import com.example.tonwalletapp.presentation.theme.roboto

@Composable
fun AlertDialogComponent(
    title:String,
    subtitle:String,
    dismissTitle:String,
    confirmTitle:String,
    onClose:() -> Unit,
    onDismiss:() -> Unit = {},
    onConfirm:() -> Unit = {}
) {

    AlertDialog(
        properties = DialogProperties(),
        modifier = Modifier.padding(bottom = 10.dp),
        onDismissRequest = { onClose() },
        title = {
            Text(
                text = title,
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 19.sp,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = subtitle,
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black
            )
        },
        confirmButton = {
            Column() {
                Row {
                    Spacer(modifier = Modifier.width(20.dp))
                    ClickableText(
                        text = AnnotatedString(confirmTitle),
                        onClick = {
                            onConfirm()
                        },
                        style = TextStyle(
                            fontFamily = roboto,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Color(0xFF1A81CF)
                        )
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            

        },
        dismissButton = {
            ClickableText(
                text = AnnotatedString(dismissTitle),
                onClick = {
                    onDismiss()
                },
                style = TextStyle(
                    fontFamily = roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color(0xFF1A81CF)
                )

            )
        }
    )

}