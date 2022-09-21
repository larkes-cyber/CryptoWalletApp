package com.example.cryptowalletapp.data.database.repository

import com.example.cryptowalletapp.data.database.entity.UserDataEntity

interface DatabaseRepositoryInterface {

    suspend fun insertUserData(userDataEntity: UserDataEntity)
    suspend fun getUserData():UserDataEntity

}