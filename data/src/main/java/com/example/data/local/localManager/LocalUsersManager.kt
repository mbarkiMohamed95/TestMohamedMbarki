package com.example.data.local.localManager

import com.example.data.local.entitys.LocalUserModel

interface LocalUsersManager {
    suspend fun saveUser(user: LocalUserModel)
    suspend fun saveUserList(users: List<LocalUserModel>)
    suspend fun getLoadedPage():Int
    suspend fun saveLoadedPage(page: Int)
    suspend fun deleteAllUsers()
    suspend fun loadAllUsers(): List<LocalUserModel>
    suspend fun loadAllUsersAsResult(): Result<List<LocalUserModel>>
    suspend fun loadUserById(id: String): LocalUserModel
    suspend fun searchUser(
        searchKey:String
    ): List<LocalUserModel>

}