package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.wallet_data_source.WalletDiskDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSource
import com.example.tonwalletapp.domain.repository.WalletRepository

class WalletRepositoryImpl(
    private val walletDiskDataSource: WalletDiskDataSource,
    private val walletTonDataSource: WalletTonDataSource
):WalletRepository {
    override suspend fun getSecretWords(): List<String> = walletTonDataSource.getSecretWords()
    override suspend fun createWallet() {
        val words = getSecretWords()
        if(words.isEmpty()){
            walletTonDataSource.setupWallet(walletTonDataSource.generateWords())
        }
    }

    override suspend fun importWallet(words: List<String>) {
        walletTonDataSource.setupWallet(words)
    }
}