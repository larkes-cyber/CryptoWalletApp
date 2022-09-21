package com.example.cryptowalletapp.domain.repository

import com.example.cryptowalletapp.domain.model.UserData

interface AuthRepository {
    suspend fun saveUserData(userData:UserData)
    suspend fun getUserData():UserData
}