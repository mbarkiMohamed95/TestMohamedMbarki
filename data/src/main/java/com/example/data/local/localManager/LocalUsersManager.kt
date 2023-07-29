package com.example.data.local.localManager

import androidx.paging.PagingSource
import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow

interface LocalUsersManager {
    suspend fun saveUser(user: LocalUserModel)
    suspend fun deleteAllUsers()
    fun loadAllUsers(): PagingSource<Int, LocalUserModel>
    suspend fun loadUserById(id: String): LocalUserModel
    suspend fun searchUser(
        searchKey:String
    ): List<LocalUserModel>

}