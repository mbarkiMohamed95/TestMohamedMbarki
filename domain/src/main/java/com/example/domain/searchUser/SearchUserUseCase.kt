package com.example.domain.searchUser

import com.example.domain.repo.model.UserModelDto


interface SearchUserUseCase {
    suspend operator fun invoke(
        searchKey:String
    ): List<UserModelDto>

}