package com.example.data.networking.userInfo.manager

import com.example.data.networking.services.ApiServices
import com.example.data.networking.userInfo.model.UserResponse
import com.example.data.tools.runCatchingResponse


class UserNetworkManagerImp constructor(private val apiService: ApiServices) : UserNetworkManager {
    override suspend fun loadUsers(page: Int): Result<UserResponse> = Result.runCatchingResponse {
        apiService.loadUserInfo(page)
    }

}