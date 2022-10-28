package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.repository.CoinRepository

class UseGetCurrencyLogoBySym(val coinRepository: CoinRepository) {

    fun execute(syb:String):String{
        return coinRepository.getCoinLogoSrc(syb)
    }

}