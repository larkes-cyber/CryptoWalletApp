package com.example.tonwalletapp.data.ton_remote.model

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519

data class WalletTon(
    val publicKey: PublicKeyEd25519,
    val privateKey: PrivateKeyEd25519,
    val address:String,
    val words:List<String>,
    var initialized: Boolean
)