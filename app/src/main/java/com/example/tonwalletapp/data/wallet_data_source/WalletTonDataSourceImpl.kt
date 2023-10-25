package com.example.tonwalletapp.data.wallet_data_source

import com.example.tonwalletapp.data.remote.TonClient
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.data.remote.model.WalletTon
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.mnemonic.Mnemonic

class WalletTonDataSourceImpl(
    private val tonClient: TonClient
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
        val address = MsgAddressInt.toString(walletContract.address)

        val init = tonClient.getWalletBalance(address) != null


        return WalletTon(
            privateKey = privateKey,
            publicKey = publicKey,
            address = address,
            words = words,
            initialized = init
        )
    }

    override suspend fun makeTransfer(walletEntity: WalletTon, address: String, amount:Double) {
        tonClient.makeTransfer(walletTon = walletEntity, address = address, amount = amount)
    }

    override suspend fun getWalletTransactions(address: String): List<TransactionDetailTon> {
        return tonClient.getWalletTransaction(address)
    }

    override suspend fun getWalletBalance(address: String): Float? {
        return tonClient.getWalletBalance(address)
    }

}