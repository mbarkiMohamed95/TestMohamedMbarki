package com.example.domain.userDetail

import com.example.domain.repo.UserRepository
import com.example.domain.userDetail.model.UserDetailModel

class UserDetailUseCaseImp constructor(private val userRepository: UserRepository) :
    UserDetailUseCase {
    override suspend fun invoke(id: String): UserDetailModel = userRepository.loadUserById(id).run {
        UserDetailModel(fullName, email, location, phone, picture, gender)
    }

}