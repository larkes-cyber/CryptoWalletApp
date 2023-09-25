package com.example.tonwalletapp.data.wallet_data_source

import org.ton.mnemonic.Mnemonic

class WalletRemoteDataSourceImpl:WalletRemoteDataSource {
    override suspend fun generateSecretWords(): List<String> {
        return Mnemonic.generate()
    }
}