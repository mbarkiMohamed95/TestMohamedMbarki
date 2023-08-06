package com.example.data.local

import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel


class LocalDataBaseUserTest(private val dao: UserDao) {
    suspend fun insetUser(weatherLocalModel: LocalUserModel) {
        dao.insert(weatherLocalModel)
    }

    fun getAllUsers(): List<LocalUserModel> = dao.loadUsers()

    suspend fun deleteAllUsers() {
        dao.deleteAll()
    }
    suspend fun searchUser(
        searchKey: String
    ): List<LocalUserModel> = dao.searchUser("%${searchKey}%")
}