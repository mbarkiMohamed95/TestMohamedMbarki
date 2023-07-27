package com.example.CoTest.presentation.detailUser.action

sealed class DetailViewAction {
    data class LoadUserDetail(val id: String) : DetailViewAction()
}
