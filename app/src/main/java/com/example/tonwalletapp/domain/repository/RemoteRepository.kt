package com.example.tonwalletapp.domain.repository

interface RemoteRepository {

    suspend fun getSecretWords():List<String>

}