package com.example.data.networking.userInfo.manager

import com.example.data.networking.userInfo.model.UserResponse

interface UserNetworkManager {
    suspend fun loadUsers(page:Int):Result<UserResponse>
}