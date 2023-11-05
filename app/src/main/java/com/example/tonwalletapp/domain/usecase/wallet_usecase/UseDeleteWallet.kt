package com.example.tonwalletapp.domain.usecase.wallet_usecase

import com.example.tonwalletapp.domain.repository.WalletRepository

class UseDeleteWallet(
    private val walletRepository: WalletRepository
) {
    suspend fun execute(){
        walletRepository.deleteWallet()
    }
}