package com.example.data.di

import androidx.room.Room
import com.example.data.local.LocalDataBaseUserTest
import com.example.data.local.dataBaseManager.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val localTestModule = module {
    single() {
        Room.inMemoryDatabaseBuilder(androidContext(), AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    single() {
        provideTestUserDao(get())
    }
    single() {
        LocalDataBaseUserTest(get())
    }
}

private fun provideTestUserDao(appDatabase: AppDatabase) = appDatabase.userDao()


