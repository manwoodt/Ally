package com.study.ally

import android.app.Application
import com.study.ally.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AllyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AllyApp)
            modules(appModule)
        }
    }
}