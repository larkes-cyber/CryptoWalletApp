package com.example.tonwalletapp.domain.mapper

import com.example.tonwalletapp.data.remote.model.TransactionDetailTon
import com.example.tonwalletapp.domain.model.TransactionDetail
import java.time.LocalDate

fun TransactionDetailTon.toTransactionDetail():TransactionDetail{
    return TransactionDetail(
        fromWalletAddress = fromWalletAddress,
         amount = amount,
         fee = fee,
         message = message,
         time = time
    )
}