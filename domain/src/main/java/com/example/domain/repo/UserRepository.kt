package com.example.domain.repo

import com.example.domain.repo.model.UserDetailDtoModel
import com.example.domain.repo.model.UserModelDto
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    fun loadUsersAsFlow(page: Int): Flow<Result<List<UserModelDto>>>
    suspend fun loadUsers(page: Int): Result<List<UserModelDto>>
    suspend fun loadUserById(id: String): UserDetailDtoModel
    suspend fun deleteAllUsers()

    suspend fun searchUser(
        searchKey: String = ""
    ): List<UserModelDto>
}
