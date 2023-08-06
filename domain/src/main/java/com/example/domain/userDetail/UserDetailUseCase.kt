package com.example.domain.userDetail

import com.example.domain.repo.model.UserDetailDtoModel


interface UserDetailUseCase {
    suspend operator fun invoke(id: String): UserDetailDtoModel

}