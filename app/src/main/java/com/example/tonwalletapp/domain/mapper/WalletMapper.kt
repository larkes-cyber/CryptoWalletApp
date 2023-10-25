package com.example.tonwalletapp.domain.mapper

import android.util.Log
import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.domain.model.Wallet
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.mnemonic.Mnemonic

fun Wallet.toWalletEntity():WalletEntity{
    return WalletEntity(
        id = id,
        words = words.toWordsString(),
        name = name,
        address = address,
        initialized = initialized
    )
}

fun WalletEntity.toWallet():Wallet{

    val keyPair = Mnemonic.toSeed(mnemonic = words.toWordsList())
    val privateKey = PrivateKeyEd25519.of(keyPair)
    val publicKey = PublicKeyEd25519.of(privateKey)

    return Wallet(
        id = id,
        words = words.toWordsList(),
        name = name,
        privateKey = privateKey,
        publicKey = publicKey,
        address = address,
        initialized = initialized
    )
}

fun WalletTon.toWallet():Wallet{
    return Wallet(
        publicKey = publicKey,
        privateKey = privateKey,
        address = address,
        words = words,
        initialized = initialized
    )
}

fun Wallet.toWalletTon():WalletTon{
    return WalletTon(
        publicKey = publicKey,
        privateKey = privateKey,
        address = address,
        words = words,
        initialized = initialized
    )
}

fun WalletTon.toWalletEntity():WalletEntity{
    return this.toWallet().toWalletEntity()
}

fun List<String>.toWordsString():String{
    return this.joinToString(",")
}

fun String.toWordsList():List<String>{
    return this.split(",")
}