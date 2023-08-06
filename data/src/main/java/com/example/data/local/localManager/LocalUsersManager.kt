package com.example.data.local.localManager

import androidx.paging.PagingSource
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow

interface LocalUsersManager {
    suspend fun saveUser(user: LocalUserModel)
    suspend fun saveUserList(users: List<LocalUserModel>)
    suspend fun getLoadedPage():Int
    suspend fun saveLoadedPage(page: Int)
    suspend fun deleteAllUsers()
    fun loadAllUsers(): List<LocalUserModel>
    fun loadAllUsersAsResult(): Result<List<LocalUserModel>>
    suspend fun loadUserById(id: String): LocalUserModel
    suspend fun searchUser(
        searchKey:String
    ): List<LocalUserModel>

}