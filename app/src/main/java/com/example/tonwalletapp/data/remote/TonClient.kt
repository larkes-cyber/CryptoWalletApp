package com.example.tonwalletapp.data.remote

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import org.ton.api.pk.PrivateKeyEd25519

interface TonClient {
    suspend fun makeTransfer(walletTon: WalletTon, address:String, amount:Double)
    suspend fun getWalletTransaction(address: String):List<TransactionDetailTon>
    suspend fun getWalletBalance(address: String):Float?
    suspend fun getWalletAddress(privateKeyEd25519: PrivateKeyEd25519):String
    suspend fun checkWalletInitialization(address: String):Boolean
}