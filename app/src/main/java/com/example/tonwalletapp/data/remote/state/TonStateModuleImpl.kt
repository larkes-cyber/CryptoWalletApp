package com.example.tonwalletapp.data.remote.state

import com.example.tonwalletapp.until.Constants.SOURCE_CODE
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.StateInit
import org.ton.boc.BagOfCells
import org.ton.cell.CellBuilder
import org.ton.contract.wallet.WalletContract
import org.ton.contract.wallet.v4.AbstractContractV4.Companion.DEFAULT_WALLET_ID
import org.ton.crypto.base64

class TonStateModuleImpl:TonStateModule {

    private val sourceCode = BagOfCells(base64(SOURCE_CODE)).first()

    override fun createStateInit(privateKeyEd25519: PrivateKeyEd25519):StateInit {
        return StateInit(
            sourceCode,
            createDataInit(privateKey = privateKeyEd25519)
        )
    }

    private fun createDataInit(
        subWalletId:Int = DEFAULT_WALLET_ID + 0,
        privateKey:PrivateKeyEd25519
    ) = CellBuilder.createCell {
        storeUInt(0, 32)
        storeUInt(subWalletId, 32)
        storeBytes(privateKey.publicKey().key.toByteArray())
        storeBit(false)
    }
}