package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.wallet_data_source.WalletRemoteDataSource
import com.example.tonwalletapp.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow

class WalletRepositoryImpl(
    private val walletRemoteDataSource: WalletRemoteDataSource
):WalletRepository {
    override suspend fun generateSecretWords(): List<String> = walletRemoteDataSource.generateSecretWords()
}