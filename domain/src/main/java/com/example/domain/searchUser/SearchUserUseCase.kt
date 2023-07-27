package com.example.domain.searchUser

import androidx.paging.PagingData
import com.example.domain.loadUseCase.model.UserModel
import kotlinx.coroutines.flow.Flow

interface SearchUserUseCase {
    suspend operator fun invoke(
        searchKey:String
    ): List<UserModel>

}