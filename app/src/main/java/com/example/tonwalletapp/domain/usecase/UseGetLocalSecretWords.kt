package com.example.tonwalletapp.domain.usecase

import com.example.tonwalletapp.domain.repository.LocalRepository

class UseGetLocalSecretWords(
   private val localRepository: LocalRepository
) {

   fun execute():List<String>{
       return localRepository.getSecretWords()
   }

}