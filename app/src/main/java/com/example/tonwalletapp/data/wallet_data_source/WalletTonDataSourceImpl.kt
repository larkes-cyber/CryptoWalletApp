package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import org.ton.mnemonic.Mnemonic

class WalletTonDataSourceImpl(
    private val tonLiteClient: TonLiteClient
):WalletTonDataSource {

    private var _secretWords = listOf<String>()
    override suspend fun setupWallet(words: List<String>) {
        tonLiteClient.setUpWallet(words)
    }

    override suspend fun generateWords():List<String> {
        _secretWords = Mnemonic.generate()
        return _secretWords
    }
    override fun getSecretWords(): List<String> {
        return _secretWords
    }
}