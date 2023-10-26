package com.example.tonwalletapp.data.remote.wallet

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import org.ton.api.pub.PublicKeyEd25519
import org.ton.lite.api.LiteApi

interface TonWalletModule {

    suspend fun getSeqno(address:String):Int?
    suspend fun getWalletAddress(publicKeyEd25519: PublicKeyEd25519):String
    suspend fun getTransactionList(address: String):List<TransactionDetailTon>
    suspend fun getWalletBalance(address: String):Float?
    suspend fun checkWalletInitialization(address: String):Boolean

}