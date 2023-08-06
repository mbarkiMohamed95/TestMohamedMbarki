package com.example.CoTest.base

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.CoTest.di.servicesModule
import com.example.CoTest.di.viewModelsProvider
import com.example.base.di.dataLayerModule
import com.example.base.di.domainLayerModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : MultiDexApplication() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@BaseApplication)
            // Load modules
            modules(dataLayerModule, domainLayerModules, viewModelsProvider, servicesModule)
        }
    }

}