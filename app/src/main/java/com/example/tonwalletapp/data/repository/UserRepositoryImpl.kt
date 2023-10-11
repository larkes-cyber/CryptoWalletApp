package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.user_data_source.UserDiskDataSource
import com.example.tonwalletapp.data.user_data_source.UserTonDataSource
import com.example.tonwalletapp.data.wallet_data_source.WalletDiskDataSource
import com.example.tonwalletapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDiskDataSource: UserDiskDataSource,
    private val walletDiskDataSource: WalletDiskDataSource,
    private val userTonDataSource: UserTonDataSource
):UserRepository {
    override fun savePassCode(pass: String) {
        userDiskDataSource.savePassCode(pass)
    }
    override fun getPassCode(): String = userDiskDataSource.getPassCode()
    override suspend fun cleanUpUserData() {
        walletDiskDataSource.nukeWalletTable()
    }

    override suspend fun initializeTonLiteClient() {
        userTonDataSource.initializeTonLiteClient()
    }

}