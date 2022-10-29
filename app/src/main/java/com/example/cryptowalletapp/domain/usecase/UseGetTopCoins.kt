package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.repository.CoinRepository

class UseGetTopCoins(val coinRepository: CoinRepository) {

    suspend fun execute():List<CoinInfo>{

        val data = coinRepository.getTopCoinsMarket()
        val output = ArrayList<CoinInfo>()

        data.forEach {
            val src = coinRepository.getCoinLogoSrc(it.coinInfo!!.name!!)
            val history = coinRepository.getCoinHistory(it.coinInfo.id!!)
            output.add(CoinInfo(
                name = it.coinInfo.fullName!!,
                symbol = it.coinInfo.name!!,
                src = src,
                price = history[history.size-1],
                priceHistory = history
            ))
        }

        return output

    }

}