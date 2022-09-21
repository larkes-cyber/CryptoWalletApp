package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.model.UserData
import com.example.cryptowalletapp.domain.repository.AuthRepository

class UseInsertUserData(val authRepository: AuthRepository) {

    suspend fun execute(userData: UserData){
        authRepository.saveUserData(userData)
    }

}