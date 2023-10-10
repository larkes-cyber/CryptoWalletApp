package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.data.remote.ton.TonLiteClient
import com.example.tonwalletapp.data.remote.ton.TonLiteClientImpl
import org.ton.mnemonic.Mnemonic

class WalletTonDataSourceImpl(
    private val tonLiteClient: TonLiteClient
):WalletTonDataSource {

    private var _secretWords = listOf<String>()


    override suspend fun generateWords():List<String> {
        _secretWords = Mnemonic.generate()
        return _secretWords
    }
    override fun getSecretWords(): List<String> {
        return _secretWords
    }

    override suspend fun getWalletInfo(words: List<String>): WalletTon {
        return tonLiteClient.getWalletInfo(words)
    }
}