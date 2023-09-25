package com.example.tonwalletapp.data.repository

import com.example.tonwalletapp.data.user_data_source.UserDiskDataSource
import com.example.tonwalletapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDiskDataSource: UserDiskDataSource
):UserRepository {
    override fun savePassCode(pass: String) {
        userDiskDataSource.savePassCode(pass)
    }
    override fun getPassCode(): String = userDiskDataSource.getPassCode()
}