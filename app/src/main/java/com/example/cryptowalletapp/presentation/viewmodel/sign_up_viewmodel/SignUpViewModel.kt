package com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {

    fun arrayToString(str:MutableList<String>):String{

        var string = ""
        str.forEach { string += it }

        return string

    }

    fun checkEmail(email:String):Boolean{

        var letterBefore = ArrayList<String>()
        Log.d("emaill",email)

        for(i in email.length-1 downTo 0){
            if(email[i].toString() == "@"){
                break
            }
            letterBefore.add(email[i].toString())
        }

        letterBefore.reverse()

        if(email.isEmpty()) return true

        if(email.length == letterBefore.size) return true


        val stringLetter = arrayToString(letterBefore)
        Log.d("letter", stringLetter)

        val possibleDomains = arrayOf("gmail.com","ru")
        if (stringLetter !in possibleDomains) return true

        return false
    }

    fun checkPass(pass:String):Boolean{
        return pass.length > 3
    }

}