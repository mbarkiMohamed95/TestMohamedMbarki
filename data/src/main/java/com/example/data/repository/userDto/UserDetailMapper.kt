package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.data.repository.user.model.UserDetailRepoModel
import com.example.data.repository.user.model.userDetailRepoModel

class UserDetailMapper : MappingService<LocalUserModel, UserDetailRepoModel> {

    override fun mapInputToOutput(input: LocalUserModel): UserDetailRepoModel = userDetailRepoModel {
        fullName = "${input.name?.title} ${input.name?.first} ${input.name?.last}"
        email = input.email
        phone = input.phone
        location =
            "${input.location?.street}, ${input.location?.city}, ${input.location?.state}, ${input.location?.country} ,${input.location?.postcode}"
        picture = input.picture?.large ?: ""
        gender = input.gender
    }

}