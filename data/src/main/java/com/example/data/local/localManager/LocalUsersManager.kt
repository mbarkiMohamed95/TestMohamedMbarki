package com.example.data.local.localManager

import com.example.data.local.entitys.LocalUserModel
import kotlinx.coroutines.flow.Flow

interface LocalUsersManager {
    suspend fun saveUserList(users: List<LocalUserModel>)
    suspend fun saveUser(user: LocalUserModel)
    fun loadAllUsersAsFlow(): Flow<List<LocalUserModel>>
}