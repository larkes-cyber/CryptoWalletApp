package com.example.tonwalletapp.presentation.view

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.presentation.theme.roboto
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants
import com.example.tonwalletapp.until.Constants.TonWalletText
import com.example.tonwalletapp.until.Constants.TonWalletTitle

@Composable
fun InfoScreenSkeleton(
    modifier: Modifier = Modifier,
    image:Int = R.drawable.ton_crystal_frame,
    imageSize:Int = 95,
    title:String = TonWalletTitle,
    subtitle:String = TonWalletText,
    btnTitle:String = "",
    optionalBtnTitle:String = "",
    visibleBtn:Boolean = false,
    visibleOptionalBtn:Boolean = false,
    error:String = "",
    onBtnClick:() -> Unit = {},
    onOptionBtnClick:() -> Unit = {},
    body:@Composable ()() -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(imageSize.dp)
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(vertical = 12.dp),
            color = AppTheme.colors.primaryTitle,
            textAlign = TextAlign.Center
            )
        Text(
            text = subtitle,
            fontSize = 15.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.primaryTitle
        )

        body()

        if(error.isNotEmpty()){
            Text(text = error, fontSize = 12.sp, color = Color.Red)
        }

        if(visibleBtn) {
            PrimaryButtonApp(
                modifier = Modifier.width(200.dp),
                text = btnTitle
            ) {
                onBtnClick()
            }
        }
        if(!visibleOptionalBtn && visibleBtn) Spacer(modifier = Modifier.height(60.dp))
        if(visibleOptionalBtn) {
            Spacer(modifier = Modifier.height(22.dp))
            ClickableText(
                text = AnnotatedString(optionalBtnTitle),
                onClick = {
                    onOptionBtnClick()
                },
                style = MaterialTheme.typography.button.copy(color = AppTheme.colors.primaryBackground)
            )
        }
    }

}