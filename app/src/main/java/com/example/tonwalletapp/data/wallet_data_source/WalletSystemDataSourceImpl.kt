package com.example.tonwalletapp.data.wallet_data_source

import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE

class WalletSystemDataSourceImpl(
    private val context: Context
):WalletSystemDataSource {
    override fun readClipboard(): String {
        val clipBoardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        var text:String? = null
        clipBoardManager.addPrimaryClipChangedListener {
            val copiedString = clipBoardManager.primaryClip?.getItemAt(0)?.text?.toString()
            text = copiedString
        }
        return ""
    }
}