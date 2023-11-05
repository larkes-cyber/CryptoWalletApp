package com.example.tonwalletapp.data.ton_remote.module.wallet

import com.example.tonwalletapp.data.ton_remote.model.TransactionDetailTon
import org.ton.api.pub.PublicKeyEd25519

interface TonWalletModule {

    suspend fun getSeqno(address:String):Int?
    suspend fun getWalletAddress(publicKeyEd25519: PublicKeyEd25519):String
    suspend fun getTransactionList(address: String):List<TransactionDetailTon>?
    suspend fun getWalletBalance(address: String):Float?
    suspend fun checkWalletInitialization(address: String):Boolean

}