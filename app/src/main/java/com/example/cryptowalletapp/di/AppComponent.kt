package com.example.cryptowalletapp.di

import com.example.cryptowalletapp.data.database.repository.DatabaseRepository
import com.example.cryptowalletapp.presentation.fragment.AlreadyThereFragment
import com.example.cryptowalletapp.presentation.fragment.HomeFragment
import com.example.cryptowalletapp.presentation.fragment.LoadingFragment
import com.example.cryptowalletapp.presentation.fragment.SignUpFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun injectSignUpFragment(signUpFragment: SignUpFragment)
    fun injectDatabaseRepository(databaseRepository: DatabaseRepository)
    fun injectLoadingFragment(loadingFragment: LoadingFragment)
    fun injectAlreadyThere(alreadyThereFragment: AlreadyThereFragment)
    fun injectHomeFragment(homeFragment: HomeFragment)

}