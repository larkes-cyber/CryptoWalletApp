package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.presentation.component.InvalidAddressAlert
import com.example.tonwalletapp.ui.theme.AppTheme
import com.example.tonwalletapp.until.Constants.SEND_TON_TITLE
import com.example.tonwalletapp.presentation.component.PrimaryButtonApp
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view.EnterAddressSubView
import com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton.sub_view.EnterAmountSubView
import com.example.tonwalletapp.until.Constants.ContinueBtnText
import com.example.tonwalletapp.until.Constants.ENTER_ADDRESS_TRANSFER_PROGRESS
import com.example.tonwalletapp.until.Constants.ENTER_AMOUNT_TRANSFER_PROGRESS

@Composable
fun SendTonView(
    viewController: SendTonViewController = hiltViewModel(),
    walletDetail: WalletDetail,
    onBack:() -> Unit
) {

    val sendTonUIState by viewController.sendTonUIState.collectAsState()

    LaunchedEffect(Unit){
        viewController.initWalletInfo(walletDetail)
    }

    LaunchedEffect(sendTonUIState.transferProgress){
        if(sendTonUIState.transferProgress <= 0) {
            onBack()
            viewController.resetTonSendViewData()
        }
    }


   Column(modifier = Modifier
       .fillMaxWidth()
       .padding(top = 12.dp)){
       Row(verticalAlignment = Alignment.CenterVertically) {
           IconButton(onClick = {
                viewController.progressBack()
           }) {
               Icon(
                   imageVector = Icons.Default.ArrowBack,
                   contentDescription = "",
                   modifier = Modifier.size(24.dp),
                   tint = AppTheme.colors.primaryTitle
               )
           }
           Text(
               text = SEND_TON_TITLE,
               fontSize = 20.sp,
               color = AppTheme.colors.secondBackground,
               fontWeight = FontWeight.Medium,
               modifier = Modifier.padding(horizontal = 20.dp)
           )
       }
       Spacer(modifier = Modifier.height(24.dp))

       Box(modifier = Modifier.padding(horizontal = 20.dp)) {
           when(sendTonUIState.transferProgress){
               ENTER_ADDRESS_TRANSFER_PROGRESS -> {
                   EnterAddressSubView(
                       address = sendTonUIState.receiverAddress ?: "",
                       invalidAddrExp = sendTonUIState.invalidAddressException,
                       onCloseClick = {
                           viewController.resetAddressExp()
                       },
                       onDone = {
                           viewController.continueTransfer()
                       },
                       onAddressChange = {
                           viewController.onAddressChange(it)
                       }
                   )
               }
               ENTER_AMOUNT_TRANSFER_PROGRESS -> {
                   EnterAmountSubView(
                       maxAmount = sendTonUIState.totalTonAmount,
                       receiverAddress = sendTonUIState.receiverAddress!!
                   ){

                   }
               }
           }
       }
   }
}

@Composable
fun OptionButtonWrapper(
    title:String,
    icon:Int,
    onClick:() -> Unit = {}
) {
    Button(
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = AppTheme.colors.primaryBackground
            )
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AppTheme.colors.primaryBackground
            )
        }
    }
}