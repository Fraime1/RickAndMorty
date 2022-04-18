package com.fraime.android.rm.presentation.app

import android.app.Application
import com.fraime.android.rm.presentation.di.appModule
import com.fraime.android.rm.presentation.di.dataModule
import com.fraime.android.rm.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(appModule, domainModule, dataModule)
        }

    }
}