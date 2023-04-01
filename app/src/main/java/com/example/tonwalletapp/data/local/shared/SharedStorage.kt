package com.example.tonwalletapp.data.local.shared

import android.content.Context

class SharedStorage(
    private val context: Context
):SharedStorageInterface {

    private val sharedPreferences =
        context.getSharedPreferences("secret_words", Context.MODE_PRIVATE)

    override fun saveSecretWords(words: List<String>) {
        words.forEachIndexed {
                index, s ->
            sharedPreferences.edit().putString(index.toString(), s).apply()
        }
    }

    override fun getSecretWords(): List<String> {
        return (0..19).map { sharedPreferences.getString(it.toString(),"")!! }
    }
}