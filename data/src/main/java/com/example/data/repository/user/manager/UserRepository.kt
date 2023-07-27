package com.example.data.repository.user.manager

import androidx.paging.PagingData
import com.example.data.local.entitys.LocalUserModel
import com.example.data.repository.user.model.RepoUserModel
import com.example.data.repository.user.model.UserDetailRepoModel
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun loadUsersAsFlow(): Flow<PagingData<RepoUserModel>>
    fun loadUsersFromLocal(): Flow<PagingData<RepoUserModel>>

    suspend fun loadUserById(id:String): UserDetailRepoModel
}
