package com.example.data.di

import com.example.data.local.localManager.LocalUsersManager
import com.example.data.networking.services.ApiServices
import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.data.repository.user.manager.UserRepository
import com.example.data.repository.userDto.LocalToRepoUserMapper
import com.example.data.repository.userDto.RemoteToLocalUserMapper
import com.example.data.repository.userDto.UserDetailMapper
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mockito.Mockito.mock

val NetworkingTestModule = module() {
    single<ApiServices> { mock(ApiServices::class.java) }
}

val repositoryTestModule = module() {
    single<UserRepository> { mock(UserRepository::class.java) }
}