package com.example.tonwalletapp.until

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast

fun Context.getFromClipBoard() : String? {
    val clipBoardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    return clipBoardManager.primaryClip?.getItemAt(0)?.text?.toString()
}

fun Context.copyToClipboard(text: String) {
    val context:Context = this
    (getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
        setPrimaryClip(ClipData.newPlainText(null, text))
        Toast.makeText(context, "Address copied", Toast.LENGTH_LONG).show()
    }
}