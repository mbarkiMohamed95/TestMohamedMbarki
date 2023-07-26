package com.example.domain.di

import com.example.domain.loadUseCase.LoadUsersListUseCase
import com.example.domain.loadUseCase.LoadUsersListUseCaseImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domaineDi = module {
    singleOf(::LoadUsersListUseCaseImp) { bind<LoadUsersListUseCase>() }
}