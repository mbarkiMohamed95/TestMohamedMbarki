package com.example.data.di

import com.example.data.networking.services.ApiServices
import com.example.domain.loadUseCase.UserRepository
import org.koin.dsl.module
import org.mockito.Mockito.mock

val NetworkingTestModule = module() {
    single<ApiServices> { mock(ApiServices::class.java) }
}

val repositoryTestModule = module() {
    single<com.example.domain.loadUseCase.UserRepository> { mock(com.example.domain.loadUseCase.UserRepository::class.java) }
}