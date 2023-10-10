package com.example.tonwalletapp.data.remote.ton

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.WalletTon

interface TonLiteClient {
    suspend fun getWalletInfo(words:List<String>):WalletTon

}