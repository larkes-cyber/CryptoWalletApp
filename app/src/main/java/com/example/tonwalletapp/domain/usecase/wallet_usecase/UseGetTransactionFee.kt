package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository

class UseGetTransactionFee(
    private val walletRepository: WalletRepository
) {

    fun execute(amount:Float):Float{
        return 0.009f
    }

}