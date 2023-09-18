package com.example.tonwalletapp.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tonwalletapp.R
import com.example.tonwalletapp.until.Constants.TonWalletText
import com.example.tonwalletapp.until.Constants.TonWalletTitle

@Composable
fun InfoScreenSkeleton(
    modifier: Modifier = Modifier,
    image:Int = R.drawable.ton_crystal_frame,
    title:String = TonWalletTitle,
    subtitle:String = TonWalletText,
    btnTitle:String = "",
    optionalBtnTitle:String = "",
    visibleBtn:Boolean = false,
    visibleOptionalBtn:Boolean = false,
    body:@Composable() () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }

}