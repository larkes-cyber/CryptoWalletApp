package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseCreateWallet(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            walletRepository.createWallet()
            emit(Resource.Success("success"))
        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }
    }

}