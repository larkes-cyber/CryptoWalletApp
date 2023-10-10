package com.example.tonwalletapp.data.wallet_data_source

import android.util.Log
import com.example.tonwalletapp.data.database.dao.WalletDao
import com.example.tonwalletapp.data.database.entity.WalletEntity
import org.ton.mnemonic.Mnemonic

class WalletDiskDataSourceImpl(
    private val walletDao: WalletDao
):WalletDiskDataSource {
    override suspend fun insertWallet(wallet: WalletEntity) {
        walletDao.insertWallet(wallet)
    }

    override suspend fun getWallets(): List<WalletEntity> {
       return walletDao.observeAllLocalWallets()
    }

    override suspend fun getWalletById(id: String): WalletEntity {
        return walletDao.observeWalletById(id)
    }

    override suspend fun deleteWallet(id: String) {
        walletDao.deleteWallet(getWalletById(id))
    }

    override suspend fun nukeWalletTable() {
        walletDao.nukeTable()
    }


}