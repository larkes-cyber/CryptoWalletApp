package com.example.tonwalletapp.data.remote.model

import java.time.LocalDate

data class TransactionDetailTon(
    val fromWalletAddress:String,
    val amount:Float,
    val fee:Float,
    val message:String,
    val time: LocalDate
)