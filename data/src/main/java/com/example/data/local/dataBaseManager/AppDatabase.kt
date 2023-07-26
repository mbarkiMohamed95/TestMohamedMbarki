package com.example.data.local.dataBaseManager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.typeCoverter.UserTypeConverter

@Database(
    entities = [LocalUserModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(UserTypeConverter::class)

abstract class AppDatabase: RoomDatabase() {
  abstract fun userDao(): UserDao
    companion object {
        val DATABASE_NAME: String = "WeatherDataBase"
    }
}