package com.example.cryptowalletapp.data.repositoty

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.data.retrofit.repository.RetrofitRepository
import com.example.cryptowalletapp.domain.repository.CoinRepository

class CoinRepositoryData(
    val retrofitRepository: RetrofitRepository
):CoinRepository {

    override suspend fun getCoinHistory(id: String): List<Int> {
        return retrofitRepository.getCoinHistory(id)
    }

    override suspend fun getCoinInfo(id: String): CoinDetail {
        return retrofitRepository.getCoinInfo(id)
    }

}