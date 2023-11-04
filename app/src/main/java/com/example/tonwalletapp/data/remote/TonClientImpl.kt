package com.example.tonwalletapp.data.remote

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import com.example.tonwalletapp.data.remote.transfer.TonTransferModule
import com.example.tonwalletapp.data.remote.wallet.TonWalletModule
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.StateInit
import org.ton.cell.buildCell
import org.ton.tlb.storeTlb

class TonClientImpl(
    private val tonStateModule: TonStateModule,
    private val tonTransferModule: TonTransferModule,
    private val tonWalletModule: TonWalletModule
):TonClient {
    override suspend fun makeTransfer(walletTon: WalletTon, address: String, amount:Double, message:String?) {
        tonTransferModule.makeTransfer(
            walletTon = walletTon,
            address = address,
            amount = amount,
            message = message
        )
    }

    override suspend fun getWalletTransaction(address: String):List<TransactionDetailTon>? {
        return tonWalletModule.getTransactionList(address)
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return tonWalletModule.getWalletBalance(address)
    }

    override suspend fun getWalletAddress(privateKeyEd25519: PrivateKeyEd25519): String {
        return AddrStd(0, buildCell { storeTlb(StateInit, tonStateModule.createStateInit(privateKeyEd25519)) }.hash()).toString(userFriendly = true)
    }

    override suspend fun checkWalletInitialization(address: String): Boolean {
        return tonWalletModule.checkWalletInitialization(address)
    }


}