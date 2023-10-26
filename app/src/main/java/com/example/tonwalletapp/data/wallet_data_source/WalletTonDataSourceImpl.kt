package com.example.tonwalletapp.data.wallet_data_source

import android.util.Log
import com.example.tonwalletapp.data.remote.TonClient
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.state.TonStateModule
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.bitstring.BitString
import org.ton.block.AddrStd
import org.ton.block.MsgAddressInt
import org.ton.block.StateInit
import org.ton.block.toMaybe
import org.ton.cell.buildCell
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.mnemonic.Mnemonic
import org.ton.tlb.storeTlb

class WalletTonDataSourceImpl(
    private val tonClient: TonClient
):WalletTonDataSource {
    override suspend fun createWallet(): WalletTon {
        val words = Mnemonic.generate()
        return getWalletInfoByWords(words)
    }
    override suspend fun getWalletInfoByWords(words: List<String>):WalletTon {
        val myWords = listOf("history", "drill", "void", "female", "toe", "stable", "input", "neutral","throw", "settle", "endless", "come", "lazy", "cherry", "account", "logic", "firm", "stumble", "hero", "secret", "panic", "scare", "mechanic", "army")
        val seedPhrase = Mnemonic.toSeed(myWords)
        val privateKey = PrivateKeyEd25519.of(seedPhrase)
        val publicKey = PublicKeyEd25519(privateKey)
        val address = tonClient.getWalletAddress(privateKey)
        val init = tonClient.checkWalletInitialization(address)


        return WalletTon(
            privateKey = privateKey,
            publicKey = publicKey,
            address = address,
            words = words,
            initialized = init
        )
    }

    override suspend fun makeTransfer(walletEntity: WalletTon, address: String, amount:Double) {
        tonClient.makeTransfer(walletTon = walletEntity, address = address, amount = amount)
    }

    override suspend fun getWalletTransactions(address: String): List<TransactionDetailTon>? {
        return tonClient.getWalletTransaction(address)
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return tonClient.getWalletBalance(address)
    }


}