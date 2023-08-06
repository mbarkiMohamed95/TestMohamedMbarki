package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.example.domain.repo.model.UserLocationModelDto
import com.example.domain.repo.model.UserModelDto
import com.example.domain.repo.model.UserNameModelDto
import com.example.domain.repo.model.UserPictureModelDto

class LocalToRepoUserMapper  : MappingService<LocalUserModel, UserModelDto> {
    override fun mapInputToOutput(input: LocalUserModel): UserModelDto = input.run {
        UserModelDto(
            uuid,
            UserNameModelDto(title ?: "", firstName ?: "", lastName ?: ""),
            email,
            phone,
            gender,
            mapToRepoUserLocationModel(input.location),
            mapToRepoUserPictureModel(input.picture),
            pageNumber
        )
    }

    private fun mapToRepoUserLocationModel(location: LocalUserLocationModel?) = location?.run {
        UserLocationModelDto(
            location.street,
            location.city,
            location.state,
            location.country,
            location.postcode
        )
    }


    private fun mapToRepoUserPictureModel(picture: LocalUserPictureModel?) = picture?.run {
        UserPictureModelDto(
            picture.large,
            picture.medium,
            picture.thumbnail
        )
    }
}



