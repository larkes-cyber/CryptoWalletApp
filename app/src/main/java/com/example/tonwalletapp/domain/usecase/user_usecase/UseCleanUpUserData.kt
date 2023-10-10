package com.example.tonwalletapp.domain.usecase.user_usecase

import com.example.tonwalletapp.domain.repository.UserRepository
import com.example.tonwalletapp.domain.repository.WalletRepository

class UseCleanUpUserData(
    private val userRepository: UserRepository
) {

    suspend fun execute(){
        userRepository.cleanUpUserData()
    }

}