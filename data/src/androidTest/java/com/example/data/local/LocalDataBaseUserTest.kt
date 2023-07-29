package com.example.data.local

import androidx.paging.PagingSource
import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel


class LocalDataBaseUserTest(private val dao: UserDao) {
    suspend fun insetUser(weatherLocalModel: LocalUserModel) {
        dao.insert(weatherLocalModel)
    }

    fun getAllUsers(): PagingSource<Int, LocalUserModel> = dao.loadAllUsers()

    suspend fun deleteAllUsers() {
        dao.deleteAll()
    }
}