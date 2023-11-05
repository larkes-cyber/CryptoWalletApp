package com.example.tonwalletapp.data.ton_remote.module.transfer

import com.example.tonwalletapp.data.ton_remote.model.WalletTon

interface TonTransferModule {
    suspend fun makeTransfer(walletTon:WalletTon, amount:Double, address:String, message:String?)

}