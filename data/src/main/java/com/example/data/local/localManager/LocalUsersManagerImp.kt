package com.example.data.local.localManager

import androidx.paging.PagingSource
import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow


class LocalUsersManagerImp constructor(private val userDao: UserDao) : LocalUsersManager {
    override suspend fun saveUser(user: LocalUserModel) {
        userDao.insert(user)
    }

    override suspend fun saveUserList(users: List<LocalUserModel>) {
        userDao.insertList(users)
    }

    override suspend fun deleteAllUsers() = userDao.deleteAll()

    override fun loadAllUsers(): List<LocalUserModel> = userDao.loadUsers()
    override fun loadAllUsersAsResult(): Result<List<LocalUserModel>> {
        return if (userDao.loadUsers()
                .isEmpty()
        ) Result.success(userDao.loadUsers()) else Result.failure(Exception("empty data"))
    }

    override suspend fun loadUserById(id: String): LocalUserModel = userDao.loadUserById(id)
    override suspend fun searchUser(
        searchKey: String
    ): List<LocalUserModel> = userDao.searchUser("%${searchKey}%")
}
