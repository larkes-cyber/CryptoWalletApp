package com.example.tonwalletapp.domain.model


data class TransactionDetail(
    val amount:Float,
    val fee:Float,
    val message:String,
    val time:String,
    val recipientAddr:String?,
    val senderAddr:String?,
    val comment:String?,
    val storageFee:Float,
    val transactionType:String,
    val status:String
)