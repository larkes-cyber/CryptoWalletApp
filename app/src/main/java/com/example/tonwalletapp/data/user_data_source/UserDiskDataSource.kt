package com.example.tonwalletapp.data.user_data_source

interface UserDiskDataSource {
    fun savePassCode(pass:String)
    fun getPassCode():String
}