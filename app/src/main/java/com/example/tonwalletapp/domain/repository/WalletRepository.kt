package com.example.tonwalletapp.domain.repository

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.domain.model.Wallet

interface WalletRepository {
    suspend fun getSecretWords(): List<String>
    suspend fun createWallet()
    suspend fun importWallet(words:List<String>)
    suspend fun insertWallet(wallet: WalletEntity)
    suspend fun observeWallets():List<WalletEntity>
}