package com.example.data.repository.user.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.localUserModel
import com.example.data.local.entitys.location
import com.example.data.local.entitys.picture
import com.example.data.networking.userInfo.model.Results

class RemoteToLocalUserMapper : MappingService<Results, LocalUserModel> {
    override fun mapInputToOutput(input: Results): LocalUserModel = localUserModel {
        uuid = input.login.uuid
        email = input.email
        phone = input.phone
        gender = input.gender
        firstName = input.name.first
        lastName = input.name.last
        title = input.name.title

        location {
            state = input.location.state
            street = "${input.location.street.number} + ${input.location.street.name}"
            city = input.location.city
            country = input.location.country
            postcode = input.location.postcode
        }
        picture {
            large = input.picture.large
            medium = input.picture.medium
            thumbnail = input.picture.thumbnail
        }
    }
}



