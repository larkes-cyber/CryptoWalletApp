package com.example.cryptowalletapp.domain.usecase

import com.example.cryptowalletapp.domain.repository.AuthRepository

class UseCheckAlreadyUser(val authRepository: AuthRepository) {

    suspend fun execute():Boolean{
        val data = authRepository.getUserData()
        return data.email != "null"
    }

}