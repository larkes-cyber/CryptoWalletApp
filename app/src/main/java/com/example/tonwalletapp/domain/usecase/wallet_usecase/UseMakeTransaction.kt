package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.mapper.toRoundAmount
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseMakeTransaction(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(senderAddr:String, amount:Float, receiverAddr:String):Flow<Resource<String>> = flow{

        emit(Resource.Loading())
        try {
            val gas = 0.009
            val total = amount - gas
            val wallet = walletRepository.getWalletByAddress(senderAddr)

            walletRepository.makeTransfer(
                wallet = wallet,
                address = receiverAddr,
                amount = total.toFloat().toRoundAmount()
            )
            emit(Resource.Success("success"))

        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}