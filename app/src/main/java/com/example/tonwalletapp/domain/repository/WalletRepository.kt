package com.example.tonwalletapp.domain.repository

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.domain.model.Wallet
import com.example.tonwalletapp.domain.model.WalletDetail

interface WalletRepository {
    suspend fun importWallet(words:List<String>)
    suspend fun createWallet():WalletEntity
    suspend fun getWalletWords(address:String):List<String>
    suspend fun getWallets():List<WalletEntity>
    suspend fun getWalletTransactions(address: String):List<TransactionDetailTon>
    suspend fun makeTransfer(wallet: Wallet, address: String)
    suspend fun getWalletByAddress(address: String):WalletEntity

}