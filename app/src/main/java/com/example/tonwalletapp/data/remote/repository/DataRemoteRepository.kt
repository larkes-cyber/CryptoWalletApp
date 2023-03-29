package com.example.tonwalletapp.data.remote.repository

import com.example.tonwalletapp.domain.repository.RemoteRepository
import org.ton.mnemonic.Mnemonic

class DataRemoteRepository():RemoteRepository {
    override suspend fun getSecretWords(): List<String> {
        return Mnemonic.generate()
    }
}