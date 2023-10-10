package com.example.tonwalletapp.data.repository

import android.util.Log
import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.wallet_data_source.WalletDiskDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSource
import com.example.tonwalletapp.domain.mapper.toTransactionDetail
import com.example.tonwalletapp.domain.mapper.toWalletEntity
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.domain.repository.WalletRepository

class WalletRepositoryImpl(
    private val walletDiskDataSource: WalletDiskDataSource,
    private val walletTonDataSource: WalletTonDataSource
):WalletRepository {
    override suspend fun getSecretWords(): List<String> = walletTonDataSource.getSecretWords()
    override suspend fun createWallet() {
        val words = getSecretWords()
        if(words.isEmpty()){
            val wallet = walletTonDataSource.getWalletInfo(walletTonDataSource.generateWords())
            walletDiskDataSource.insertWallet(wallet = wallet.toWalletEntity())
        }
    }

    override suspend fun importWallet(words: List<String>) {
        val wallet = walletTonDataSource.getWalletInfo(words)
        walletDiskDataSource.insertWallet(wallet = wallet.toWalletEntity())
    }

    override suspend fun insertWallet(wallet: WalletEntity) {
        walletDiskDataSource.insertWallet(wallet)
    }

    override suspend fun observeWallets(): List<WalletEntity> = walletDiskDataSource.getWallets()
    override suspend fun getDetailWalletInfo(id: String): WalletDetail {

        val wallet = walletDiskDataSource.getWalletById(id)
        val walletDetail = walletTonDataSource.getDetailWalletInfo(wallet.address)

        return WalletDetail(
            name = wallet.name,
            address = wallet.address,
            balance = walletDetail.balance,
            transactions = walletDetail.transactions.map { it.toTransactionDetail() }
        )

    }

    override suspend fun getAllWallets(): List<WalletEntity> {
        return walletDiskDataSource.getWallets()
    }
}