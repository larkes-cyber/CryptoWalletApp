package com.example.tonwalletapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    suspend fun generateSecretWords(): List<String>
}