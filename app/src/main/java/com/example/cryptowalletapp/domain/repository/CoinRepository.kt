package com.example.cryptowalletapp.domain.repository

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail

interface CoinRepository {
    suspend fun getCoinHistory(id:String):List<Int>
    suspend fun getCoinInfo(id:String): CoinDetail
}