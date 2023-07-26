package com.example.data.di

import com.example.data.repository.user.manager.UserRepository
import com.example.data.repository.user.manager.UserRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::UserRepositoryImp) { bind <UserRepository> () }
}
