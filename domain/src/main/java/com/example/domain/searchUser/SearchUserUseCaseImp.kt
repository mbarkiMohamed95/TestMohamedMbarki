package com.example.domain.searchUser

import com.example.domain.repo.UserRepository
import com.example.domain.loadUseCase.model.UserModel

class SearchUserUseCaseImp(private val userRepository: UserRepository) : SearchUserUseCase {

    override suspend fun invoke(
        searchKey: String
    ): List<UserModel> =
        userRepository.searchUser(searchKey).map {
            UserModel(
                it.uuid,
                it.name?.first ?: "",
                it.name?.last ?: "",
                it.picture?.thumbnail ?: "",
                it.phone
            )
        }

}