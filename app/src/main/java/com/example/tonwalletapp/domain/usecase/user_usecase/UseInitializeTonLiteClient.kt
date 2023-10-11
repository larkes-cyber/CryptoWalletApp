package com.example.tonwalletapp.domain.usecase.user_usecase

import com.example.tonwalletapp.domain.repository.UserRepository
import com.example.tonwalletapp.until.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseInitializeTonLiteClient(
    private val userRepository: UserRepository
) {

    suspend fun execute():Resource<String>{
        return try {
            userRepository.initializeTonLiteClient()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}