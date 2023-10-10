package com.example.tonwalletapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tonwalletapp.data.database.dao.WalletDao
import com.example.tonwalletapp.data.database.entity.WalletEntity

@Database(entities = [WalletEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun walletDao():WalletDao
}