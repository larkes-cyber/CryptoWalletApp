package com.example.tonwalletapp.data.wallet_data_source

import android.util.Log
import com.example.tonwalletapp.data.database.dao.WalletDao
import com.example.tonwalletapp.data.database.entity.WalletEntity
import org.ton.mnemonic.Mnemonic

class WalletDiskDataSourceImpl(
    private val walletDao: WalletDao
):WalletDiskDataSource {
    override suspend fun insertWallet(wallet: WalletEntity) {
        Log.d("fsdfsdfsdfsdfsdf","##############################")
        wallet.id = wallet.address
        walletDao.insertWallet(wallet)
    }

    override suspend fun getWallets(): List<WalletEntity> {
       return walletDao.observeAllLocalWallets()
    }

    override suspend fun getWalletByAddress(address: String): WalletEntity {
       return walletDao.observeWalletById(address)
    }


    override suspend fun deleteWallet(id: String) {
        walletDao.deleteWallet(getWalletByAddress(id))
    }

    override suspend fun nukeWalletTable() {
        walletDao.nukeTable()
    }


}