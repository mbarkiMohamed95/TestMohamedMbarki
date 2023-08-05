package com.example.domain.repo.model



data class RepoUserModel(
    var uuid: String = "",
    var name: RepoUserNameModel? = null,
    var email: String = "",
    var phone: String = "",
    var gender: String = "",
    var location: RepoUserLocationModel? = null,
    var picture: RepoUserPictureModel? = null
)

data class RepoUserLocationModel(
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postcode: String = "",
)

data class RepoUserNameModel(
    var title: String = "",
    var first: String = "",
    var last: String = ""
)

data class RepoUserPictureModel(
    var large: String = "",
    var medium: String = "",
    var thumbnail: String = ""
)

