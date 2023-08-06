package com.example.domain.repo.user

import com.example.domain.repo.user.model.UserDetailDtoModel
import com.example.domain.repo.user.model.UserModelDto
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun loadUsers(page: Int): Result<List<UserModelDto>>
    suspend fun loadUserById(id: String): UserDetailDtoModel
    suspend fun deleteAllUsers()

    suspend fun searchUser(
        searchKey: String = ""
    ): List<UserModelDto>
}
