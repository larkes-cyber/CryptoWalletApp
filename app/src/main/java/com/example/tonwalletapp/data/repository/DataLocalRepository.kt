package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.local.shared.SharedStorage
import com.example.tonwalletapp.domain.repository.LocalRepository

class DataLocalRepository(
    private val sharedStorage: SharedStorage
):LocalRepository {
    override fun saveSecretWords(words: List<String>) {
        sharedStorage.saveSecretWords(words)
    }

    override fun getSecretWords(): List<String> {
        return sharedStorage.getSecretWords()
    }
}