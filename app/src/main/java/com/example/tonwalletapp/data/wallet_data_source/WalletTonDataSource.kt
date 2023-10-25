package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon

interface WalletTonDataSource {
    suspend fun createWallet():WalletTon
    suspend fun getWalletInfoByWords(words:List<String>):WalletTon
    suspend fun makeTransfer(walletEntity: WalletTon, address:String, amount:Double)
    suspend fun getWalletTransactions(address: String):List<TransactionDetailTon>
    suspend fun getWalletBalance(address: String):Float?
}