package com.example.domain.searchUser

import com.example.domain.repo.UserRepository
import com.example.domain.repo.model.UserModelDto

class SearchUserUseCaseImp(private val userRepository: UserRepository) : SearchUserUseCase {

    override suspend fun invoke(
        searchKey: String
    ): List<UserModelDto> =
        userRepository.searchUser(searchKey)

}