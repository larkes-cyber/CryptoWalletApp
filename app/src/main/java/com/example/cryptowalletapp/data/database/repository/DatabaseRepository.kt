package com.example.cryptowalletapp.data.database.repository

import android.content.Context
import com.example.cryptowalletapp.App.App
import com.example.cryptowalletapp.data.database.dao.UserDataDao
import com.example.cryptowalletapp.data.database.entity.UserDataEntity
import javax.inject.Inject

class DatabaseRepository(
    private val context: Context
):DatabaseRepositoryInterface {

    init {
        (context as App).appComponent.injectDatabaseRepository(this)
    }

    @Inject
    lateinit var userDataDao:UserDataDao

    override suspend fun insertUserData(userDataEntity: UserDataEntity) {
        userDataDao.insertUserData(userDataEntity)
    }

    override suspend fun getUserData(): UserDataEntity {
        return userDataDao.getUserData()
    }
}