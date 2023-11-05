package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.ton_remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.wallet_data_source.WalletDiskDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletTonDataSource
import com.example.tonwalletapp.domain.mapper.toWallet
import com.example.tonwalletapp.domain.mapper.toWalletEntity
import com.example.tonwalletapp.domain.mapper.toWalletTon
import com.example.tonwalletapp.domain.mapper.toWordsList
import com.example.tonwalletapp.domain.repository.WalletRepository

class WalletRepositoryImpl(
    private val walletDiskDataSource: WalletDiskDataSource,
    private val walletTonDataSource: WalletTonDataSource
):WalletRepository {
    override suspend fun importWallet(words: List<String>):WalletEntity {
        val wallet = walletTonDataSource.getWalletInfoByWords(words)
        walletDiskDataSource.insertWallet(wallet = wallet.toWalletEntity())
        return wallet.toWalletEntity()
    }

    override suspend fun createWallet(): WalletEntity {
        val wallet = walletTonDataSource.createWallet()
        walletDiskDataSource.insertWallet(wallet = wallet.toWalletEntity())
        return wallet.toWalletEntity()
    }

    override suspend fun getWalletWords(address: String): List<String> {
        val wallet = walletDiskDataSource.getWalletByAddress(address)
        return wallet.words.toWordsList()
    }

    override suspend fun getWallets(): List<WalletEntity> {
        return walletDiskDataSource.getWallets()
    }

    override suspend fun getWalletTransactions(address: String): List<TransactionDetailTon>? {
        return walletTonDataSource.getWalletTransactions(address)
    }

    override suspend fun makeTransfer(wallet: WalletEntity, address: String, amount:Double, message:String?) {
        walletTonDataSource.makeTransfer(walletEntity = wallet.toWallet().toWalletTon(), address = address, amount = amount, message = message)
    }

    override suspend fun getWalletByAddress(address: String): WalletEntity {
        return walletDiskDataSource.getWalletByAddress(address)
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return walletTonDataSource.getWalletBalance(address)
    }

    override suspend fun deleteWallet() {
        walletDiskDataSource.nukeWalletTable()
    }

}