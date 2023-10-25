package com.example.tonwalletapp.domain.model


data class WalletDetail(
    val address:String,
    val name:String,
    val balance:Float,
    val transactions:List<TransactionDetail> = listOf(),
    val initialized:Boolean
)