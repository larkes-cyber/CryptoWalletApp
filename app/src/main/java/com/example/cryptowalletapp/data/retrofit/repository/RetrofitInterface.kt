package com.example.cryptowalletapp.data.retrofit.repository

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail

interface RetrofitInterface {
    suspend fun getCoinHistory(id:String):List<Int>
    suspend fun getCoinInfo(id:String): CoinDetail
}