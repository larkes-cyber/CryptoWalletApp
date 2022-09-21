package com.example.cryptowalletapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptowalletapp.data.database.entity.UserDataEntity

@Dao
interface UserDataDao {

    @Insert
    suspend fun insertUserData(userDataEntity: UserDataEntity)

    @Query("SELECT * FROM USERDATAENTITY")
    suspend fun getUserData():UserDataEntity

}