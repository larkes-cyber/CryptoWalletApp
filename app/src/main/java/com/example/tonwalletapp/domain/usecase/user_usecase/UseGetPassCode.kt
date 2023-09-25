package com.example.tonwalletapp.domain.usecase.user_usecase

import com.example.tonwalletapp.domain.repository.UserRepository

class UseGetPassCode(
    private val userRepository: UserRepository
) {
    fun execute():String = userRepository.getPassCode()
}