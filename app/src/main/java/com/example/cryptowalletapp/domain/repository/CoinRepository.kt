package com.example.cryptowalletapp.domain.repository

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import com.example.cryptowalletapp.data.retrofit.model.DataItemTop
import com.example.cryptowalletapp.domain.model.CoinInfo


interface CoinRepository {
    suspend fun getCoinHistory(id:String):List<Int>
    suspend fun getCoinInfo(id:String): CoinDetail
    suspend fun getTopCoinsMarket(): List<DataItemTop>
    fun getCoinLogoSrc(symbol:String):String
}