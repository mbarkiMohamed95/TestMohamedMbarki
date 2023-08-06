package com.example.domain.loadUseCase

import com.example.domain.repo.user.UserRepository
import com.example.domain.repo.user.model.UserModelDto

class LoadUsersListUseCaseImp(private val userRepository: UserRepository) : LoadUsersListUseCase {
    override suspend fun invoke(page: Int): Result<List<UserModelDto>> =
        userRepository.loadUsers(page)
}