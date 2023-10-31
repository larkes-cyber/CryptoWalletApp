package com.example.tonwalletapp.domain.model

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import java.util.*

data class Wallet(
    val id:String =  UUID.randomUUID().toString(),
    val name:String = "default",
    val privateKey: PrivateKeyEd25519,
    val publicKey: PublicKeyEd25519,
    val words:List<String>,
    val address:String,
    val initialized:Boolean
)

