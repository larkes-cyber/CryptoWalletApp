package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.ton_remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.ton_remote.model.WalletTon

interface WalletTonDataSource {
    suspend fun createWallet():WalletTon
    suspend fun getWalletInfoByWords(words:List<String>):WalletTon
    suspend fun makeTransfer(walletEntity: WalletTon, address:String, amount:Double, message:String?)
    suspend fun getWalletTransactions(address: String):List<TransactionDetailTon>?
    suspend fun getWalletBalance(address: String):Float?
}