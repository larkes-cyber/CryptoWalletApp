package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.until.Constants.NOT_STATED_AUTH_STATUS

data class WalletUIState(
    val walletDetail:WalletDetail? = null,
    val isLoading:Boolean = false,
    val error:String = ""
)