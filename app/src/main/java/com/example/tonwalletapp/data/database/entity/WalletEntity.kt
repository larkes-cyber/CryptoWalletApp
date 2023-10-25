package com.example.tonwalletapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WalletEntity(
    @PrimaryKey(autoGenerate = false)
    var id:String,
    val name:String,
    val words:String,
    val address:String,
    var initialized:Boolean
)