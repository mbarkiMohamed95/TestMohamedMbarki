package com.example.data.networking.userInfo.manager

import androidx.paging.PagingSource
import com.example.data.networking.userInfo.model.Results
import com.example.data.networking.userInfo.model.UserResponse

abstract  class UserNetworkManager: PagingSource<Int,Results>()