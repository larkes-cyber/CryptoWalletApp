package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseImportWallet(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(words:List<String>):Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            walletRepository.importWallet(words)
            emit(Resource.Success("success"))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message!!))
        }
    }

}