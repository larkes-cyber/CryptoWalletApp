package com.example.tonwalletapp.data.local.shared

interface SharedStorageInterface {

    fun saveSecretWords(words:List<String>)
    fun getSecretWords():List<String>

}