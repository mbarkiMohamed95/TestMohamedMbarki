package com.example.domain.repo.model



class UserDetailDtoModel(
    var fullName: String = "",
    var email: String = "",
    var location: String = "",
    var phone: String = "",
    var picture: String = "",
    var gender: String = "",
)

fun userDetailRepoModel(block: UserDetailDtoModel.() -> Unit): UserDetailDtoModel =
    UserDetailDtoModel().apply(block)
