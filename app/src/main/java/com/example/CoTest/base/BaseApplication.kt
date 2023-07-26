package com.example.CoTest.base

import android.app.Application
import com.example.base.di.dataLayerModule
import com.example.base.di.domainLayerModules
import com.example.CoTest.di.viewModelsProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@BaseApplication)
            // Load modules
            modules(dataLayerModule,domainLayerModules,viewModelsProvider)
        }
    }

}