package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.database.entity.WalletEntity
import com.example.tonwalletapp.data.remote.TonModule
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import com.example.tonwalletapp.domain.mapper.toWallet
import com.example.tonwalletapp.domain.mapper.toWalletTon
import com.example.tonwalletapp.domain.model.Wallet
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.MsgAddressExt
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.mnemonic.Mnemonic

class WalletTonDataSourceImpl(
    private val tonModule: TonModule
):WalletTonDataSource {
    override suspend fun createWallet(): WalletTon {
        val words = Mnemonic.generate()
        return getWalletInfoByWords(words)
    }
    override suspend fun getWalletInfoByWords(words: List<String>):WalletTon {
        val seedPhrase = Mnemonic.toSeed(words)
        val privateKey = PrivateKeyEd25519.of(seedPhrase)
        val publicKey = PublicKeyEd25519(privateKey)
        val walletContract = WalletV4R2Contract(0, publicKey)



        return WalletTon(
            privateKey = privateKey,
            publicKey = publicKey,
            address = MsgAddressInt.toString(walletContract.address),
            words = words,
            initialized = false
        )
    }

    override suspend fun makeTransfer(walletEntity: WalletTon, address: String, amount:Double) {
        tonModule.makeTransfer(walletTon = walletEntity, address = address, amount = amount)
    }

    override suspend fun getWalletTransactions(address: String): List<TransactionDetailTon> {
        return tonModule.getWalletTransaction(address)
    }

}