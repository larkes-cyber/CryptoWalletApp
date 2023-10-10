package com.example.tonwalletapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tonwalletapp.data.database.entity.WalletEntity

@Dao
interface WalletDao {

    @Insert
    suspend fun insertWallet(walletEntity: WalletEntity)

    @Query("SELECT * FROM WalletEntity where id = :id")
    suspend fun observeWalletById(id:String):WalletEntity

    @Query("SELECT * FROM WalletEntity")
    suspend fun observeAllLocalWallets():List<WalletEntity>

    @Delete
    suspend fun deleteWallet(walletEntity: WalletEntity)

}