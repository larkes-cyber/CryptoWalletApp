package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetSecretWords(
    private val walletRepository: WalletRepository
) {

    operator fun invoke():Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            val words = walletRepository.getSecretWords()
            emit(Resource.Success(words))
        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}