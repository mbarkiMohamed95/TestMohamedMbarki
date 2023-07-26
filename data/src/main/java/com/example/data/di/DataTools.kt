package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dataBaseManager.AppDatabase
import com.example.data.networking.services.ApiServices
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule = module {
    single { provideBlogDb(androidContext()) }
    single { provideWeatherDao(get()) }
    single { providesHttpLoggingInterceptor() }
    single {
        providesOkHttpClient(get())
    }
    single {
        provideListRetrofit(get())
    }
    single {
        provideApiService(get())
    }
    single(named("IoDispatcher") ) {
         providesIODispatcher()
    }
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


private fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


private fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient
        .Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(httpLoggingInterceptor)
        .build()


private fun provideListRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://randomuser.me")
    .client(okHttpClient)
    .build()


private fun provideApiService(retrofit: Retrofit): ApiServices =
    retrofit.create(ApiServices::class.java)

fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO