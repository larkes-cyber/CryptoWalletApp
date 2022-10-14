package com.example.cryptowalletapp.data.retrofit.repository

import android.util.Log
import com.example.cryptowalletapp.data.retrofit.api.CompareApi
import com.example.cryptowalletapp.data.retrofit.api.PaprikaApi
import com.example.cryptowalletapp.data.retrofit.model.CoinDetail

class RetrofitRepository(
    val paprikaApi: PaprikaApi,
    val compareApi: CompareApi
):RetrofitInterface {

    override suspend fun getCoinHistory(id: String): List<Int> {

        Log.d("use_get_coin_history","start")

        val action = compareApi.getCryptoHistory("BTC","USD",4)

        val data = action.body()

        val output:MutableList<Int> = ArrayList()

        Log.d("crypto_d",data.toString())

        data?.data?.data?.forEach {
            output.add(it!!.close!!.toInt())
        }


        Log.d("use_get_coin_history","code:${action.code()}")

        return output

    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        Log.d("use_get_coin_info","start")

        val action = paprikaApi.getCoinById(id)

        Log.d("use_get_coin_info","code:${action.code()}")

        return action.body()!!
    }


}