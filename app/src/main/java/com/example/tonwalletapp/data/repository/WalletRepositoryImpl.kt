package com.example.tonwalletapp.data.repository

import android.util.Log
import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.wallet_data_source.WalletDiskDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSource
import com.example.tonwalletapp.domain.mapper.toTransactionDetail
import com.example.tonwalletapp.domain.mapper.toWalletEntity
import com.example.tonwalletapp.domain.model.Wallet
import com.example.tonwalletapp.domain.model.WalletDetail
import com.example.tonwalletapp.domain.repository.WalletRepository

class WalletRepositoryImpl(
    private val walletDiskDataSource: WalletDiskDataSource,
    private val walletTonDataSource: WalletTonDataSource
):WalletRepository {
    override suspend fun importWallet(words: List<String>) {
        TODO("Not yet implemented")
    }

    override suspend fun createWallet(): WalletEntity {
        TODO("Not yet implemented")
    }

    override suspend fun getWalletWords(address: String): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getWallets(): List<WalletEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getWalletTransactions(address: String): List<TransactionDetailTon> {
        TODO("Not yet implemented")
    }

    override suspend fun makeTransfer(wallet: Wallet, address: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getWalletByAddress(address: String): WalletEntity {
        TODO("Not yet implemented")
    }

}