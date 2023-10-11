package com.example.tonwalletapp.data.user_data_source

interface UserTonDataSource {

    suspend fun initializeTonLiteClient()

}