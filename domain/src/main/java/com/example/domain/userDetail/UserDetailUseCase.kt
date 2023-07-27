package com.example.domain.userDetail

import com.example.domain.userDetail.model.UserDetailModel


interface UserDetailUseCase {
    suspend operator fun invoke(id: String): UserDetailModel

}