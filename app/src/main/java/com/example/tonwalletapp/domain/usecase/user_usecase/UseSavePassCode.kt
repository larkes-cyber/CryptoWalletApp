package com.example.tonwalletapp.domain.usecase.user_usecase

import com.example.tonwalletapp.domain.repository.UserRepository

class UseSavePassCode(
    private val userRepository: UserRepository
) {

    fun execute(passCode:String){
        userRepository.savePassCode(passCode)
    }

}