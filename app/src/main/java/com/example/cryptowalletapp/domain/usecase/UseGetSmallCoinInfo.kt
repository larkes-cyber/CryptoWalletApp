package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.repository.CoinRepository

class UseGetSmallCoinInfo(val coinRepository: CoinRepository) {

    suspend fun execute(id:String):CoinInfo{

        val dataFromPaprika = coinRepository.getCoinInfo(id)
        val history = coinRepository.getCoinHistory(id)

        return CoinInfo(
            name = dataFromPaprika.name!!,
            symbol = dataFromPaprika.symbol!!,
            src = dataFromPaprika.logo!!,
            price = history[-1],
            priceHistory = history
        )

    }

}