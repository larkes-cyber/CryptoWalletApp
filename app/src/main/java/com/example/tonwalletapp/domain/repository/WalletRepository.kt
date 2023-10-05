package com.example.tonwalletapp.domain.repository

interface WalletRepository {
    suspend fun getSecretWords(): List<String>
    suspend fun createWallet()
}