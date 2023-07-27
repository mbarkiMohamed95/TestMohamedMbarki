package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.example.data.repository.user.model.RepoUserLocationModel
import com.example.data.repository.user.model.RepoUserModel
import com.example.data.repository.user.model.RepoUserNameModel
import com.example.data.repository.user.model.RepoUserPictureModel

class LocalToRepoUserMapper : MappingService<LocalUserModel, RepoUserModel> {
    override fun mapInputToOutput(input: LocalUserModel): RepoUserModel = input.run {
        RepoUserModel(
            uuid,
            RepoUserNameModel(title ?: "", firstName ?: "", lastName ?: ""),
            email,
            phone,
            gender,
            mapToRepoUserLocationModel(input.location),
            mapToRepoUserPictureModel(input.picture)
        )
    }

    private fun mapToRepoUserLocationModel(location: LocalUserLocationModel?) = location?.run {
        RepoUserLocationModel(
            location.street,
            location.city,
            location.state,
            location.country,
            location.postcode
        )
    }


    private fun mapToRepoUserPictureModel(picture: LocalUserPictureModel?) = picture?.run {
        RepoUserPictureModel(
            picture.large,
            picture.medium,
            picture.thumbnail
        )
    }
}



