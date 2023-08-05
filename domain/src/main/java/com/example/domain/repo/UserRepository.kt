package com.example.domain.repo

import androidx.paging.PagingData
import com.example.domain.repo.model.RepoUserModel
import com.example.domain.repo.model.UserDetailRepoModel

import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun loadUsersAsFlow(page: Int): Flow<Result<List<RepoUserModel>>>
    suspend fun loadUsers(page: Int): Result<List<RepoUserModel>>
    suspend fun loadUserById(id: String): UserDetailRepoModel
    suspend fun deleteAllUsers()

    suspend fun searchUser(
        searchKey: String = ""
    ): List<RepoUserModel>
}
