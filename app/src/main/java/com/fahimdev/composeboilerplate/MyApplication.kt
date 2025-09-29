package com.fahimdev.composeboilerplate

import android.app.Application
import com.fahimdev.composeboilerplate.di.appModule
import com.fahimdev.data.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule, repoModule)
        }
    }
}