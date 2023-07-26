package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.localUserModel
import com.example.data.local.entitys.location
import com.example.data.local.entitys.name
import com.example.data.local.entitys.picture
import com.example.data.networking.userInfo.model.Results

class RemoteToLocalUserMapper : MappingService<Results, LocalUserModel> {
    override fun mapInputToOutput(input: Results): LocalUserModel = localUserModel {
        uuid = input.login.uuid
        email = input.email
        phone = input.phone
        gender = input.gender
        location {
            state = input.location.state
            street = "${input.location.street.number} + ${input.location.street.name}"
            city = input.location.city
            country = input.location.country
            postcode = input.location.postcode
        }
        name {
            title = input.name.title
            first = input.name.first
            last = input.name.last
        }
        picture {
            large = input.picture.large
            medium = input.picture.medium
            thumbnail = input.picture.thumbnail
        }

    }
}



