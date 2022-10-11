package com.example.cryptowalletapp.data.retrofit.repository

import com.example.cryptowalletapp.data.retrofit.api.CompareApi
import com.example.cryptowalletapp.data.retrofit.api.PaprikaApi
import com.example.cryptowalletapp.data.retrofit.model.CoinDetail

class RetrofitRepository(
    val paprikaApi: PaprikaApi,
    val compareApi: CompareApi
):RetrofitInterface {

    override suspend fun getCoinHistory(id: String): List<Int> {
        val data = compareApi.getCryptoHistory(id,"USD",2)

        val output:MutableList<Int> = ArrayList()

        data.data?.data?.forEach {
            it?.close?.let { it1 -> output.add(it1.toInt()) }
        }

        return data as List<Int>

    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        return paprikaApi.getCoinById(id)
    }


}