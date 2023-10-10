package com.example.tonwalletapp.data.remote.model

import com.example.tonwalletapp.domain.model.TransactionDetail

data class WalletDetailTon(
    val balance:Float,
    val transactions:List<TransactionDetailTon> = listOf()
)