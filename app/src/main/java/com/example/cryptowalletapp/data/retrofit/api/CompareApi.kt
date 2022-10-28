package com.example.cryptowalletapp.data.retrofit.api

import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import com.example.cryptowalletapp.data.retrofit.model.CryptoHistory
import com.example.cryptowalletapp.data.retrofit.model.CurrencyPrice
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CompareApi {

    @GET("data/price?fsym={coinSymbol}&tsyms=USD,JPY,EUR")
    suspend fun getPriceOfCurrency(
        @Path("coinSymbol") coinSymbol:String
    ):Response<CurrencyPrice>

    @GET("data/v2/histohour")
    suspend fun getCryptoHistory(
        @Query("fsym") fsym:String,
        @Query("tsym") tsym:String,
        @Query("limit") limit:Int
    ):Response<CryptoHistory>

    @GET("data/top/mktcapfull")
    suspend fun getCryptoTop(
        @Query("tsym") tsym:String,
        @Query("limit") limit:Int
    ):Response<CoinTop>
}