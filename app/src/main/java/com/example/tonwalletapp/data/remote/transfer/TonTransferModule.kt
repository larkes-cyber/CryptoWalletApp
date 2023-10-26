package com.example.tonwalletapp.data.remote.transfer

import com.example.tonwalletapp.data.remote.model.WalletTon

interface TonTransferModule {
    suspend fun makeTransfer(walletTon:WalletTon, amount:Double, address:String)

}