package com.example.data.repository.user.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.domain.repo.user.model.UserDetailDtoModel
import com.example.domain.repo.user.model.userDetailRepoModel

class UserDetailMapper : MappingService<LocalUserModel, UserDetailDtoModel> {

    override fun mapInputToOutput(input: LocalUserModel): UserDetailDtoModel =
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