package com.example.tonwalletapp.domain.repository

interface WalletRepository {
    suspend fun getSecretWords(): List<String>
    suspend fun createWallet()
    suspend fun importWallet(words:List<String>)
}