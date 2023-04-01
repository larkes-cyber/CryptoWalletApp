package com.example.tonwalletapp.domain.usecase

import android.util.Log
import com.example.tonwalletapp.domain.repository.RemoteRepository
import com.example.tonwalletapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetSecretWords(
    private val remoteRepository: RemoteRepository
) {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            val words = remoteRepository.getSecretWords()
            emit(Resource.Success(words))
        }catch (e:Exception){
            emit(Resource.Error("Server error"))
        }

    }

}