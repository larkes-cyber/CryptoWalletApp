package com.example.tonwalletapp.domain.model

import java.time.LocalDate

data class TransactionDetail(
    val fromWalletAddress:String,
    val amount:Float,
    val fee:Float,
    val message:String,
    val time:LocalDate
)