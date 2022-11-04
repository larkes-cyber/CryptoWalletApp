package com.example.cryptowalletapp.data.retrofit.repository

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import retrofit2.Response

interface RetrofitInterface {
    suspend fun getCoinHistory(id:String):List<Double>
    suspend fun getCoinInfo(id:String): CoinDetail
    fun getCoinLogoBySymbol(sym:String):String
    suspend fun getCoinsTopMarket():Response<CoinTop>
}