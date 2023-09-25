package com.example.tonwalletapp.data.user_data_source

import android.content.Context

class UserDiskDataSourceImpl(
    private val context:Context
):UserDiskDataSource {

    private val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    override fun savePassCode(pass: String) {
        sharedPreferences.edit().putString("pass_code", pass).apply()
    }

    override fun getPassCode(): String = sharedPreferences.getString("pass_code", "none")!!
}