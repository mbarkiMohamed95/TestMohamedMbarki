package com.example.domain.di

import com.example.domain.loadUseCase.LoadUsersListUseCase
import com.example.domain.loadUseCase.LoadUsersListUseCaseImp
import com.example.domain.searchUser.SearchUserUseCase
import com.example.domain.searchUser.SearchUserUseCaseImp
import com.example.domain.userDetail.UserDetailUseCase
import com.example.domain.userDetail.UserDetailUseCaseImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domaineDi = module {
    factoryOf(::LoadUsersListUseCaseImp) { bind<LoadUsersListUseCase>() }
    factoryOf(::UserDetailUseCaseImp) { bind<UserDetailUseCase>() }
    factoryOf(::SearchUserUseCaseImp) { bind<SearchUserUseCase>() }

}