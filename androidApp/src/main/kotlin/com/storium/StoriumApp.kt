package com.storium

import android.app.Application
import com.storium.di.initKoin
import com.storium.di.storage.storageModule
import org.koin.android.ext.koin.androidContext

class StoriumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(platformModules = listOf(storageModule)).apply {
            androidContext(this@StoriumApp)
        }
    }
}
