package com.example.cryptowalletapp.data.repositoty

import com.example.cryptowalletapp.data.database.entity.UserDataEntity
import com.example.cryptowalletapp.data.database.repository.DatabaseRepository
import com.example.cryptowalletapp.domain.model.UserData
import com.example.cryptowalletapp.domain.repository.AuthRepository

class AuthRepositoryData (
    val databaseRepository: DatabaseRepository
        ):AuthRepository {
    override suspend fun saveUserData(userData: UserData) {
        databaseRepository.insertUserData(userData.toUserDataEntity())
    }

    override suspend fun getUserData(): UserData {
        return databaseRepository.getUserData().toUserData()
    }
}

fun UserDataEntity.toUserData():UserData{
    return UserData(
        email = email,
        pass = pass,
        firstName = firstName,
        lastName = lastName,
        sub = sub,
        pincode = pincode
    )
}

fun UserData.toUserDataEntity():UserDataEntity{
    return UserDataEntity(
        email = email,
        pass = pass,
        firstName = firstName,
        lastName = lastName,
        sub = sub,
        pincode = pincode
    )
}