package com.example.tonwalletapp.domain.usecase

import com.example.tonwalletapp.domain.repository.LocalRepository
import javax.inject.Inject

class UseSaveLocalSecretWords(
    private val localRepository: LocalRepository
) {

    fun execute(words:List<String>){
        localRepository.saveSecretWords(words)
    }

}