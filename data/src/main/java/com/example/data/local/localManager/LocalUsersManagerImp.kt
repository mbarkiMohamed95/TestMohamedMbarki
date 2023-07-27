package com.example.data.local.localManager

import androidx.paging.PagingSource
import com.example.data.local.dao.userDao.UserDao
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow


class LocalUsersManagerImp constructor(private val userDao: UserDao) : LocalUsersManager {
    override suspend fun saveUser(user: LocalUserModel) {
        userDao.insert(user)
    }

    override fun loadAllUsers(): PagingSource<Int, LocalUserModel> = userDao.loadAllUsers()
    override suspend fun loadUserById(id: String): LocalUserModel = userDao.loadUserById(id)
    override suspend fun searchUser(
        searchKey: String
    ): List<LocalUserModel> = userDao.searchUser("%${searchKey}%")
}
