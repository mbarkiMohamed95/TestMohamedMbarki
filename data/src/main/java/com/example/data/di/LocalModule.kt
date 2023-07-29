package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dataBaseManager.AppDatabase
import com.example.data.local.localManager.LocalUsersManager
import com.example.data.local.localManager.LocalUsersManagerImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val localModule = module {
    single { provideBlogDb(androidContext()) }
    single { provideWeatherDao(get()) }

    singleOf(::LocalUsersManagerImp) { bind<LocalUsersManager>() }
}

private fun provideBlogDb(context: Context): AppDatabase {
    return Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

private fun provideWeatherDao(appDatabase: AppDatabase) = appDatabase.userDao()

