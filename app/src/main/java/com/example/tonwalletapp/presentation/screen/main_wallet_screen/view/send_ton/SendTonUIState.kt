package com.example.tonwalletapp.presentation.screen.main_wallet_screen.view.send_ton

import com.example.tonwalletapp.until.Constants.ENTER_ADDRESS_TRANSFER_PROGRESS

data class SendTonUIState(
    val transferProgress:Int = ENTER_ADDRESS_TRANSFER_PROGRESS,
    val invalidAddressException:Boolean = false,
    val receiverAddress:String? = null,
    val totalTonAmount:Float = 0f,
    val sendAmount:Float? = null,
    val fee:Float? = null
)