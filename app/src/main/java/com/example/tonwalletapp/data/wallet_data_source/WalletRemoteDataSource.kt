package com.example.tonwalletapp.data.wallet_data_source

interface WalletRemoteDataSource {

    suspend fun generateSecretWords():List<String>

}