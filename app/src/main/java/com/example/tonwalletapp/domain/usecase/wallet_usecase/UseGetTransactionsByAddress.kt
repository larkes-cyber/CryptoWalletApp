package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.mapper.toTransactionDetail
import com.example.tonwalletapp.domain.mapper.toWallet
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.domain.model.Wallet
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetTransactionsByAddress(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(address:String): Flow<Resource<List<TransactionDetail>?>> = flow {

        emit(Resource.Loading())
        try {
            val txt = walletRepository.getWalletTransactions(address)?.map {
                it.toTransactionDetail()
            }
            emit(Resource.Success(txt))
        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}