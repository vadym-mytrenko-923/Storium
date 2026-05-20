package com.storium

import android.app.Application
import com.storium.di.androidModule
import com.storium.di.initKoin
import org.koin.android.ext.koin.androidContext

class StoriumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(platformModules = listOf(androidModule)).apply {
            androidContext(this@StoriumApp)
        }
    }
}
