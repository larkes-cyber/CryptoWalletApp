package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Constants.FEE

class UseGetTransactionFee(
    private val walletRepository: WalletRepository
) {

    fun execute(amount:Float):Float{
        return FEE.toFloat()
    }

}