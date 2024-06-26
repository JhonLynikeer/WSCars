package com.jltech.wscars.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.jltech.wscars.di.listModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WSCarsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
        startKoin{
            androidContext(this@WSCarsApplication)
            modules(listModules)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: Context? = null
            private set
    }
}