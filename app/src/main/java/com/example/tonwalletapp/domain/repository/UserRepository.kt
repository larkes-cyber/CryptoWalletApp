package com.example.tonwalletapp.domain.repository

interface UserRepository {

    fun savePassCode(pass:String)
    fun getPassCode():String

}