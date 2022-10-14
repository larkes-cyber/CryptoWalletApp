package com.example.cryptowalletapp.domain.repository

import com.example.cryptowalletapp.data.retrofit.model.CoinDetail
import com.example.cryptowalletapp.domain.model.CoinInfo
import com.example.cryptowalletapp.domain.model.UserData

interface AuthRepository {
    suspend fun saveUserData(userData:UserData)
    suspend fun getUserData():UserData
}