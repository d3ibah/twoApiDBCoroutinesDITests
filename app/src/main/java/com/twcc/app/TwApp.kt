package com.twcc.app

import android.app.Application
import com.twcc.di.appModule
import com.twcc.di.dataModule
import com.twcc.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TwApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TwApp)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}