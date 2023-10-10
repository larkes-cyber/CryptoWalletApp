package com.example.tonwalletapp.domain.usecase.wallet_usecase

import android.util.Log
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseSetupWallet(
    private val walletRepository: WalletRepository
) {

    operator fun invoke(words:List<String> = listOf()):Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            if(words.isEmpty()) {
                Log.d("dfgfsdfgfd","#########")
                walletRepository.createWallet()
            }
            else walletRepository.importWallet(words)
            emit(Resource.Success("success"))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message!!))
        }
    }

}