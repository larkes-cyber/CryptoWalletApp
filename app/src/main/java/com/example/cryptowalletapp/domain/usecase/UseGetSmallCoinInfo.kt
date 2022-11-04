package com.example.cryptowalletapp.domain.usecase

import android.util.Log
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.repository.CoinRepository
import java.util.Collections.max

class UseGetSmallCoinInfo(val coinRepository: CoinRepository) {

    suspend fun execute(id:String,syb:String):CoinInfo{

        val dataFromPaprika = coinRepository.getCoinInfo(id)
        val history = coinRepository.getCoinHistory(syb)

        history.forEach {
            Log.d("usecase",it.toString())
        }

        return CoinInfo(
            name = dataFromPaprika.name!!,
            symbol = dataFromPaprika.symbol!!,
            src = dataFromPaprika.logo!!,
            price = max(history),
            priceHistory = history
        )

    }

}