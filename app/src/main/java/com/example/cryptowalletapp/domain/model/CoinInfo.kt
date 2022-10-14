package com.example.cryptowalletapp.domain.model

class CoinInfo(
    val name:String,
    val symbol:String,
    val src:String,
    val price:Int,
    val priceHistory:List<Int>
)