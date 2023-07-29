package com.example.data.di

import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.data.local.LocalDataBaseUserTest
import com.example.data.local.dataBaseManager.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module


val localTestModule = module {
    single(named("testdb")) {
        Room.inMemoryDatabaseBuilder(androidApplication(), AppDatabase::class.java)
            .openHelperFactory(get()).build()
    }
    single(named("testdao")) {
        provideTestUserDao(get(named("testdb")))
    }
    single(named("localDataBaseUserTest")) {
        LocalDataBaseUserTest(get(named("testdao")))
    }

}

private fun provideTestUserDao(appDatabase: AppDatabase) = appDatabase.userDao()


