package com.example.cryptowalletapp.domain.usecase

import android.util.Log
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.repository.CoinRepository
import java.util.Collections.max

class UseGetTopCoins(val coinRepository: CoinRepository) {

    suspend fun execute():List<CoinInfo>{

        val data = coinRepository.getTopCoinsMarket()
        val output = ArrayList<CoinInfo>()

        data.forEach {
            val src = coinRepository.getCoinLogoSrc(it.coinInfo!!.name!!)
            Log.d("get_coin_history_bef",it.coinInfo.name!!)
            val history = coinRepository.getCoinHistory(it.coinInfo.name!!)
            output.add(CoinInfo(
                name = it.coinInfo.fullName!!,
                symbol = it.coinInfo.name,
                src = src,
                price = max(history),
                priceHistory = history
            ))
        }

        return output

    }

}