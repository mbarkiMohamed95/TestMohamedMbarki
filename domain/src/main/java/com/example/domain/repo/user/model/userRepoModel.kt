package com.example.domain.repo.user.model



data class UserModelDto(
    var uuid: String = "",
    var name: UserNameModelDto? = null,
    var email: String = "",
    var phone: String = "",
    var gender: String = "",
    var location: UserLocationModelDto? = null,
    var picture: UserPictureModelDto? = null,
    var pageNumber: Int = 0
)

data class UserLocationModelDto(
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postcode: String = "",
)

data class UserNameModelDto(
    var title: String = "",
    var first: String = "",
    var last: String = ""
)

data class UserPictureModelDto(
    var large: String = "",
    var medium: String = "",
    var thumbnail: String = ""
)

