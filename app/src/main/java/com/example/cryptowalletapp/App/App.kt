package com.example.cryptowalletapp.App

import android.app.Application
import com.example.cryptowalletapp.di.AppComponent
import com.example.cryptowalletapp.di.AppModule
import com.example.cryptowalletapp.di.DaggerAppComponent

class App:Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()

    }

}