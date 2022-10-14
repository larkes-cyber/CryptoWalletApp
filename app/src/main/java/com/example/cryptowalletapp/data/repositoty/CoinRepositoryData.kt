package com.example.cryptowalletapp.data.repositoty

import android.util.Log
import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.repository.RetrofitRepository
import com.example.cryptowalletapp.domain.repository.CoinRepository

class CoinRepositoryData(
    val retrofitRepository: RetrofitRepository
):CoinRepository {

    override suspend fun getCoinHistory(id: String): List<Int> {

        val output = retrofitRepository.getCoinHistory(id)

        output.forEach{
            Log.d("hiistory_data",it.toString())
        }


        return output
    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        return retrofitRepository.getCoinInfo(id)
    }

}