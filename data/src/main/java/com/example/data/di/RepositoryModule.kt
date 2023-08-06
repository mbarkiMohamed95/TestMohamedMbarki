package com.example.data.di

import com.example.data.repository.user.UserRepositoryImp
import com.example.domain.repo.user.UserRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::UserRepositoryImp) { bind<UserRepository>() }
    single {
        Dispatchers.IO
    }
}
