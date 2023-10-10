package com.example.tonwalletapp.domain.usecase.user_usecase

import com.example.tonwalletapp.domain.repository.UserRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseSetupTonConfig(
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<Resource<String>> = flow {

        emit(Resource.Loading())
        try {
            userRepository.setupTonConfig()
            emit(Resource.Success("success"))
        }catch (e:Exception){
            emit(Resource.Error(e.message!!))
        }

    }

}