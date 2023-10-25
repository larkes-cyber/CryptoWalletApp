package com.example.tonwalletapp.data.remote

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.transfer.TonTransferModule
import com.example.tonwalletapp.data.remote.wallet.TonWalletModule

class TonClientImpl(
    private val tonStateModule: TonStateModule,
    private val tonTransferModule: TonTransferModule,
    private val tonWalletModule: TonWalletModule
):TonClient {
    override suspend fun makeTransfer(walletTon: WalletTon, address: String, amount:Double) {
        if(!walletTon.initialized){
            tonTransferModule.makeWalletInitTransfer(walletTon, address)
        }
        tonTransferModule.makeTransfer(
            walletTon = walletTon,
            address = address,
            amount = amount
        )
    }

    override suspend fun getWalletTransaction(address: String):List<TransactionDetailTon> {
        return tonWalletModule.getTransactionList(address)
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return tonWalletModule.getWalletBalance(address)
    }


}