package com.example.domain.loadUseCase

import androidx.paging.PagingData
import com.example.domain.loadUseCase.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LoadUsersListUseCase {
     operator fun invoke(): Flow<PagingData<UserModel>>
}