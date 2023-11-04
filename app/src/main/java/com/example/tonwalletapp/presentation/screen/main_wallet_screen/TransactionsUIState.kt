package com.example.tonwalletapp.presentation.screen.main_wallet_screen

import com.example.tonwalletapp.domain.model.TransactionDetail

data class TransactionsUIState(
    val isLoading:Boolean = false,
    val txt:List<TransactionDetail>? = null,
    val error:String = "",
    val selectedTxt:Int? = null
)