package com.example.tonwalletapp.data.ton_remote.model

data class WalletDetailTon(
    val balance:Float,
    val transactions:List<TransactionDetailTon> = listOf()
)