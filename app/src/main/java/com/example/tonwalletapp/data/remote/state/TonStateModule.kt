package com.example.tonwalletapp.data.remote.state

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.StateInit

interface TonStateModule {

    fun createStateInit(privateKeyEd25519: PrivateKeyEd25519): StateInit

}