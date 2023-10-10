package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.mapper.toWallet
import com.example.tonwalletapp.domain.model.Wallet
import com.example.tonwalletapp.domain.repository.WalletRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetAllWallets(
    private val walletRepository: WalletRepository
) {

    operator fun invoke():Flow<Resource<List<Wallet>>> = flow {

        emit(Resource.Loading())
        try {
            val wallets = walletRepository.observeWallets()
            emit(Resource.Success(wallets.map { it.toWallet() }))
        }catch (e:java.lang.Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}