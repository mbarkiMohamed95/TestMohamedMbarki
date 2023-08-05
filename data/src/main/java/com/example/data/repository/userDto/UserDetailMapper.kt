package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.domain.repo.model.UserDetailRepoModel
import com.example.domain.repo.model.userDetailRepoModel

class UserDetailMapper : MappingService<LocalUserModel, UserDetailRepoModel> {

    override fun mapInputToOutput(input: LocalUserModel): UserDetailRepoModel =
        userDetailRepoModel {
            fullName = "${input.title} ${input.firstName} ${input.lastName}"
            email = input.email
            phone = input.phone
            location =
                "${input.location?.street}, ${input.location?.city}, ${input.location?.state}, ${input.location?.country} ,${input.location?.postcode}"
            picture = input.picture?.large ?: ""
            gender = input.gender
        }

}