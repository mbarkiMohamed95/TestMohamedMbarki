package com.example.domain.repo.model



class UserDetailRepoModel(
    var fullName: String = "",
    var email: String = "",
    var location: String = "",
    var phone: String = "",
    var picture: String = "",
    var gender: String = "",
)

fun userDetailRepoModel(block: UserDetailRepoModel.() -> Unit): UserDetailRepoModel =
    UserDetailRepoModel().apply(block)
