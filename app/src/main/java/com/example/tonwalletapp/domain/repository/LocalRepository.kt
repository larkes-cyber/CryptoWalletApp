package com.example.tonwalletapp.domain.repository

interface LocalRepository {
    fun saveSecretWords(words:List<String>)
    fun getSecretWords():List<String>
}