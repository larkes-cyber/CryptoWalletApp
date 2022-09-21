package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.model.UserData
import com.example.cryptowalletapp.domain.repository.AuthRepository

class UseGetUserData(val authRepository: AuthRepository) {

    suspend fun execute():UserData{
        return authRepository.getUserData()
    }

}