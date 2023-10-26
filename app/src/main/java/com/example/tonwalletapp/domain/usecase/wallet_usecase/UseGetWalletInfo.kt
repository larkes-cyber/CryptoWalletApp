package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.mapper.toTransactionDetail
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetWalletInfo(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(address:String):Flow<Resource<WalletDetail>> = flow{
        emit(Resource.Loading())
        try {
            val wallet = walletRepository.getWalletByAddress(address)
            println(wallet)
            val balance = walletRepository.getWalletBalance(address)
            println(balance)
            val txt = walletRepository.getWalletTransactions(address)
            println(txt)

            val walletDetail = WalletDetail(
                address = wallet.address,
                name = wallet.name,
                balance = balance ?: 0f,
                transactions = txt?.map {
                println(it)
                    it.toTransactionDetail()
                                        },
                initialized = wallet.initialized
            )

            emit(Resource.Success(walletDetail))

        }catch (e:Exception){
            println(e)
            emit(Resource.Error(e.message!!))

        }
    }

}