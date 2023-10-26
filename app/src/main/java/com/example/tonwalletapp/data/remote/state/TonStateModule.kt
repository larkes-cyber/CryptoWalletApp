package com.example.tonwalletapp.data.remote.state

import com.example.tonwalletapp.data.remote.model.TonMsgType
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import io.ktor.util.hex
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AccountState
import org.ton.block.AccountStatus
import org.ton.block.AddrStd
import org.ton.block.Maybe
import org.ton.block.Message
import org.ton.block.StateInit
import org.ton.block.TrPhaseComputeVm
import org.ton.block.TransOrd
import org.ton.block.Transaction
import org.ton.block.TransactionDescr
import org.ton.cell.Cell
import org.ton.lite.client.internal.FullAccountState

interface TonStateModule {

    fun createStateInit(privateKeyEd25519: PrivateKeyEd25519): StateInit
    suspend fun getAccountState(address:String): FullAccountState?

}