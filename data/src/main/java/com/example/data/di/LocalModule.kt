package com.example.data.di

import com.example.data.local.localManager.LocalUsersManager
import com.example.data.local.localManager.LocalUsersManagerImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val localModule = module {
    singleOf(::LocalUsersManagerImp) { bind<LocalUsersManager>() }
}