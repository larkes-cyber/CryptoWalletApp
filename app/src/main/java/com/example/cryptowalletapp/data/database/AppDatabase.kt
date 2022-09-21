package com.example.cryptowalletapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptowalletapp.data.database.dao.UserDataDao
import com.example.cryptowalletapp.data.database.entity.UserDataEntity

@Database(entities = [UserDataEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDataDao():UserDataDao

}