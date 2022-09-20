package com.example.cryptowalletapp.di

import com.example.cryptowalletapp.presentation.fragment.SignUpFragment
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun injectSignUpFragment(signUpFragment: SignUpFragment)

}