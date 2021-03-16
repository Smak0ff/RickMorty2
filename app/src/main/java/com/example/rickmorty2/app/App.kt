package com.example.rickmorty2.app

import android.app.Application
import android.content.Context
import com.example.rickmorty2.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            fragmentFactory()
            androidContext(this@App)
            modules(listOf(appModule, viewModelModule, apiModule, networkModule, repositoryModule))
        }
    }
}