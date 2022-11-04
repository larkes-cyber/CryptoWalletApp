package com.example.cryptowalletapp.data.repositoty

import android.annotation.SuppressLint
import android.util.Log
import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.model.CoinTop
import com.example.cryptowalletapp.data.retrofit.model.DataItemTop
import com.example.cryptowalletapp.data.retrofit.repository.RetrofitRepository
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.repository.CoinRepository

class CoinRepositoryData(
    val retrofitRepository: RetrofitRepository
):CoinRepository {

    override suspend fun getCoinHistory(id: String): List<Double> {

        val output = retrofitRepository.getCoinHistory(id)

        output.forEach{
            Log.d("hiistory_data",it.toString())
        }


        return output
    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        return retrofitRepository.getCoinInfo(id)
    }

    @SuppressLint("LongLogTag")
    override suspend fun getTopCoinsMarket(): List<DataItemTop> {
        Log.d("use_get_top_coins_market","started")
        val resp = retrofitRepository.getCoinsTopMarket()
        return  resp.body()!!.data!!.map { it!! }
    }

    override fun getCoinLogoSrc(symbol: String):String {
        return retrofitRepository.getCoinLogoBySymbol(symbol)
    }

}