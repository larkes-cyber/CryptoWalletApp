package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.remote.model.WalletTon

interface WalletTonDataSource {
    suspend fun generateWords():List<String>
    fun getSecretWords():List<String>
    suspend fun getWalletInfo(words:List<String>):WalletTon
}