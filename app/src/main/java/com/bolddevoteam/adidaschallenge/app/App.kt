package com.bolddevoteam.adidaschallenge.app

import android.app.Application
import com.bolddevoteam.adidaschallenge.core.extensions.onlyDebug
import com.bolddevoteam.adidaschallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            if(onlyDebug()) androidLogger(Level.DEBUG)
            modules(appModule)
        }
    }

}