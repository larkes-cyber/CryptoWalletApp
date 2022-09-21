package com.example.cryptowalletapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class UserDataEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    val email:String,
    val pass:String,
    val firstName:String,
    val lastName:String,
    val sub:String,
    val pincode:String
)