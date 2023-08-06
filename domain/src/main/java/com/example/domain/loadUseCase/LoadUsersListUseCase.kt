package com.example.domain.loadUseCase

import com.example.domain.repo.model.UserModelDto

interface LoadUsersListUseCase {
    suspend operator fun invoke(page: Int): Result<List<UserModelDto>>
}