package com.example.cryptowalletapp.presentation.viewmodel.sign_up_viewmodel

import androidx.lifecycle.ViewModel

class SignUpViewModel:ViewModel() {

    fun checkEmail(email:String):Boolean{

        var letterBefore = ""

        for(i in email.length-1 downTo 0){
            if(email[i].toString() == "@"){
                break
            }
            letterBefore += email[i].toString()
        }

        if(email.length == letterBefore.length) return true

        val possibleDomains = arrayOf("gmail.com","ru")
        if (letterBefore !in possibleDomains) return true

        return false
    }

    fun checkPass(pass:String):Boolean{
        return pass.length > 3
    }

}