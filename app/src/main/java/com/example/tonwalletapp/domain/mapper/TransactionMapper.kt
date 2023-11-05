package com.example.tonwalletapp.domain.mapper

import android.util.Log
import com.example.tonwalletapp.data.remote.model.TonMsgAction
import com.example.tonwalletapp.data.remote.model.TonMsgType
import com.example.tonwalletapp.data.remote.model.TonTxMsg
import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.domain.model.TransactionDetail
import com.example.tonwalletapp.until.Constants.IN_TRANSACTION
import com.example.tonwalletapp.until.Constants.NANO_NUM
import com.example.tonwalletapp.until.Constants.OUT_TRANSACTION
import com.example.tonwalletapp.until.Constants.STATUS_TRANSACTION_DENIED
import com.example.tonwalletapp.until.Constants.STATUS_TRANSACTION_PROCESSING
import com.example.tonwalletapp.until.Constants.STATUS_TRANSACTION_SUCCESS
import com.example.tonwalletapp.until.OpCodes
import org.ton.bitstring.BitString
import org.ton.block.AccountStatus
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.block.CommonMsgInfo
import org.ton.block.Either
import org.ton.block.IntMsgInfo
import org.ton.block.Maybe
import org.ton.block.Message
import org.ton.block.MsgAddress
import org.ton.block.MsgAddressInt
import org.ton.block.StateInit
import org.ton.block.TrPhaseComputeVm
import org.ton.block.TransOrd
import org.ton.block.Transaction
import org.ton.block.TransactionDescr
import org.ton.cell.Cell
import org.ton.cell.CellSlice
import org.ton.crypto.hex
import org.ton.tlb.CellRef
import org.ton.tlb.loadTlb
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import kotlin.math.roundToInt

fun TransactionDetailTon.toTransactionDetail():TransactionDetail{

    fun getDateTime(s: Long): String? {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s * 1000)
        return sdf.format(netDate)
    }

    var senderAddr = ""
    var recipientAddr = ""
    var amount:Float = 0f
    var message:String? = null

    if(inMsg != null){
        senderAddr = inMsg.source!!
        recipientAddr = inMsg.destination!!
        amount = inMsg.value.toFloat() / NANO_NUM
        message = inMsg.comment
    }else{
        senderAddr = outMsg[0].source!!
        recipientAddr = outMsg[0].destination!!
        amount = outMsg[0].value.toFloat() / NANO_NUM
        message = outMsg[0].comment
    }


    return TransactionDetail(
         time = getDateTime(created)!!,
         amount = amount,
         fee = gasFee.toFloat() / NANO_NUM,
         message = message,
         recipientAddr = recipientAddr,
         senderAddr = senderAddr,
         comment = message,
         storageFee = storageFee!!.toFloat() / NANO_NUM,
         transactionType = if(inMsg == null) OUT_TRANSACTION else IN_TRANSACTION,
         status = if(actionSucceed == null) STATUS_TRANSACTION_PROCESSING else if(actionSucceed) STATUS_TRANSACTION_SUCCESS else STATUS_TRANSACTION_DENIED,
         txtAddress = this.hash
    )
}


fun mapTx(tx: Transaction, blockId: Int, wc: Int): TransactionDetailTon {
    val descr = readTxDescr(tx.description.value)
    val computePh = readComputePhase(descr)
    return TransactionDetailTon(
        lt = tx.lt.toLong(),
        blockId = blockId,
        account = tx.accountAddr,
        accountAddr = AddrStd(wc, tx.accountAddr).toAddrString(),
        hash = hex(tx.hash().toByteArray()),
        gasFee = tx.totalFees.coins.amount.toLong(),
        actionFwdFee = descr?.action?.value?.value?.totalFwdFees?.value?.amount?.toLong(),
        actionFee = descr?.action?.value?.value?.totalActionFees?.value?.amount?.toLong(),
        storageFee = descr?.storagePh?.value?.storageFeesCollected?.amount?.toLong(),
        computeFee = computePh?.gasFees?.amount?.toLong(),
        workchain = wc,
        init = tx.origStatus != AccountStatus.ACTIVE && tx.endStatus == AccountStatus.ACTIVE,
        inMsg = mapMsgIn(Maybe.of(tx.r1.value.inMsg.value?.value)),
        outMsg = tx.r1.value.outMsgs.toMap().entries.mapNotNull { mapMsgOut(it.value.value) },
        computeSucceed = computePh?.success,
        computeExitCode = computePh?.r1?.value?.exitCode,
        actionSucceed = descr?.action?.value?.value?.success,
        actionExitCode = descr?.action?.value?.value?.resultCode,
        created = tx.now.toLong()
    )
}
fun mapMsg(
    info: CommonMsgInfo?,
    body: Either<Cell, Cell>?,
    init: Maybe<Either<StateInit, StateInit>>?,
    msgType: TonMsgType
): TonTxMsg? {
    if (info == null) return null
    val bodyValue = body?.toPair()?.toList()?.filter { it != null && !it.isEmpty() }?.firstOrNull()

    var msgAction = when {
        (msgType.inMsg() && body?.x != null && body.x?.isEmpty() == false) -> TonMsgAction.INVOCATION
        ( init?.value == null && bodyValue == null) -> TonMsgAction.TRANSFER
        (msgType.inMsg() && init != null) -> TonMsgAction.INIT
        else -> TonMsgAction.TRANSFER
    }
    var comment: String? = null
    var opCode: Long? = null
    var ftAmount: Long? = null
    var newContractOwner: AddrStd? = null

    bodyValue?.parse {
        if (this.bits.size >= 32) {
            val tag = loadUInt(32).toLong()
            if (tag == 0xd53276db || tag == 0L) {
                msgAction = TonMsgAction.TRANSFER
                comment = String(loadRemainingBits().toByteArray())
            }
            if (tag == OpCodes.OP_SFT_MINTER_MINT.toLong())
                msgAction = TonMsgAction.INVOCATION
            if (tag == OpCodes.OP_WALLET_INTERTRANSFER.toLong() || tag == OpCodes.OP_SFT_TRANSFER_NOTIFICATION.toLong()) {
                loadUInt(64) // skip queryId
                ftAmount = loadTlb(Coins.tlbCodec()).amount.toLong()
            }
            if (tag == OpCodes.OP_NFT_TRANSFER.toLong()) {
                loadUInt(64) // skip queryId
                newContractOwner = loadTlb(MsgAddressInt.tlbCodec()) as AddrStd
            }
            opCode = tag
        }
        loadRemainingBits()
    }

    if (info is IntMsgInfo)
        return TonTxMsg(
            value = info.value.coins.amount.toLong(),
            fwdFee = info.fwd_fee.amount.toLong(),
            ihrFee = info.ihr_fee.amount.toLong(),
            source = info.src.toAddrString(),
            destination = info.dest.toAddrString(),
            createdLt = info.created_lt,
            op = opCode,
            init = init?.value != null,
            msgAction = msgAction,
            msgType = msgType,
            comment = comment
        )

    return null
}

private fun readTxDescr(d: TransactionDescr): TransOrd? {
    return when (d) {
        is TransOrd -> d
        else -> null
    }
}

private fun readComputePhase(t: TransOrd?): TrPhaseComputeVm? {
    if (t == null) return null
    return when (t.computePh) {
        is TrPhaseComputeVm -> t.computePh as TrPhaseComputeVm
        else -> null
    }
}
fun CellSlice.loadRemainingBits(): BitString {
    return BitString((this.bitsPosition until this.bits.size).map { this.loadBit() })
}
fun MsgAddress.toAddrString() = (this as AddrStd).toString(true)

private fun mapMsgOut(m: Message<Cell>) = mapMsg(
    m.info,
    m.body.trimCellRef(),
    Maybe.of(m.init.value?.trimCellRef()),
    TonMsgType.OUT
)

fun mapMsgIn(m: Maybe<Message<Cell>>) = mapMsg(
    m.value?.info,
    m.value?.body?.trimCellRef(),
    Maybe.of(m.value?.init?.value?.trimCellRef()),
    TonMsgType.IN
)

fun <X> Either<X, CellRef<X>>?.trimCellRef(): Either<X, X>? {
    if (this == null) return null
//        if (this.x == null || this.y?.value == null) return null
    return Either.of(this.x, this.y?.value)
}

fun Float.toRoundAmount():Double{
    return (this * 10000.0).roundToInt() / 10000.0
}