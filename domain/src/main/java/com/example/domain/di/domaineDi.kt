package com.example.domain.di

import com.example.domain.loadUseCase.LoadUsersListUseCase
import com.example.domain.loadUseCase.LoadUsersListUseCaseImp
import com.example.domain.searchUser.SearchUserUseCase
import com.example.domain.searchUser.SearchUserUseCaseImp
import com.example.domain.userDetail.UserDetailUseCaseImp
import com.example.domain.userDetail.UserDetailUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domaineDi = module {
    singleOf(::LoadUsersListUseCaseImp) { bind<LoadUsersListUseCase>() }
    singleOf(::UserDetailUseCaseImp) { bind<UserDetailUseCase>() }
    singleOf(::SearchUserUseCaseImp) { bind<SearchUserUseCase>() }
}