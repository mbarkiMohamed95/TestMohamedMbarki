package com.example.data.di

import com.example.data.networking.services.ApiServices
import com.example.domain.repo.user.UserRepository
import org.koin.dsl.module
import org.mockito.Mockito.mock

val NetworkingTestModule = module() {
    single<ApiServices> { mock(ApiServices::class.java) }
}

val repositoryTestModule = module() {
    single<UserRepository> { mock(UserRepository::class.java) }
}