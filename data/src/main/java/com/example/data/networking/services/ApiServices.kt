package com.example.data.networking.services

import com.example.data.networking.userInfo.model.UserResponse
import com.example.data.tools.DataConst.GET_USER_INFO_END_POINT
import com.example.data.tools.DataConst.LOADED_ITEM_NUMBER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(GET_USER_INFO_END_POINT)
    suspend fun loadUserInfo(
        @Query("page") page: Int,
        @Query("results") results: Int = LOADED_ITEM_NUMBER
        ): Response<UserResponse>
}