package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.database.entity.WalletEntity

interface WalletDiskDataSource {
    suspend fun insertWallet(wallet: WalletEntity)
    suspend fun getWallets():List<WalletEntity>
    suspend fun getWalletByAddress(address:String):WalletEntity
    suspend fun deleteWallet(id:String)
    suspend fun nukeWalletTable()

}