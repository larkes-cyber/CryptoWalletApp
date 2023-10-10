package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetAllWalletsId(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {

        emit(Resource.Loading())

        try {
            val wallets = walletRepository.getAllWallets().map { it.id }
            emit(Resource.Success(wallets))
        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}