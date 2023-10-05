package com.example.tonwalletapp.data.wallet_data_source

interface WalletTonDataSource {
    suspend fun createWallet(words:List<String>)
    suspend fun generateWords():List<String>
    fun getSecretWords():List<String>
}