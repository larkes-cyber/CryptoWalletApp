package com.example.tonwalletapp.data.ton_remote.module.state

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.StateInit
import org.ton.lite.client.internal.FullAccountState

interface TonStateModule {

    fun createStateInit(privateKeyEd25519: PrivateKeyEd25519): StateInit
    suspend fun getAccountState(address:String): FullAccountState?

}