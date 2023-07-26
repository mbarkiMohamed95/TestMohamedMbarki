package com.example.data.repository.userDto

import com.example.data.base.MappingService
import com.example.data.local.entitys.LocalUserLocationModel
import com.example.data.local.entitys.LocalUserModel
import com.example.data.local.entitys.LocalUserNameModel
import com.example.data.local.entitys.LocalUserPictureModel
import com.example.data.local.entitys.localUserModel
import com.example.data.local.entitys.location
import com.example.data.local.entitys.name
import com.example.data.local.entitys.picture
import com.example.data.networking.userInfo.model.Results
import com.example.data.repository.user.model.RepoUserLocationModel
import com.example.data.repository.user.model.RepoUserModel
import com.example.data.repository.user.model.RepoUserNameModel
import com.example.data.repository.user.model.RepoUserPictureModel

class LocalToRepoUserMapper : MappingService<LocalUserModel, RepoUserModel> {
    override fun mapInputToOutput(input: LocalUserModel): RepoUserModel = input.run {
        RepoUserModel(
            uuid,
            mapToRepoUserNameModel(input.name),
            email,
            phone,
            gender,
            mapToRepoUserLocationModel(input.location),
            mapToRepoUserPictureModel(input.picture)
        )
    }

    private fun mapToRepoUserNameModel(name: LocalUserNameModel?) = name?.run {
        RepoUserNameModel(name.title, name.first, name.last)
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



