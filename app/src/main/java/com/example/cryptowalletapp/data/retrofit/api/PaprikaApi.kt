package com.example.cryptowalletapp.data.retrofit.api

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.some.crypto.data.remote.dto.CoinDetailDto
import com.some.crypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins():List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetail


}