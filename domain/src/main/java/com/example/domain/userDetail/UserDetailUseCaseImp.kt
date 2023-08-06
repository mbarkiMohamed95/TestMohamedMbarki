package com.example.domain.userDetail

import com.example.domain.repo.user.UserRepository
import com.example.domain.repo.user.model.UserDetailDtoModel

class UserDetailUseCaseImp constructor(private val userRepository: UserRepository) :
    UserDetailUseCase {
    override suspend fun invoke(id: String): UserDetailDtoModel =
        userRepository.loadUserById(id).run {
            UserDetailDtoModel(fullName, email, location, phone, picture, gender)
        }

}