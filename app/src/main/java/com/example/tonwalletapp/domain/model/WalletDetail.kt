package com.example.tonwalletapp.domain.model


data class WalletDetail(
    var address:String,
    val name:String,
    val balance:Float,
    val initialized:Boolean
)