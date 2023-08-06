package com.example.data.di

import com.example.data.networking.userInfo.manager.UserNetworkManager
import com.example.data.networking.userInfo.manager.UserNetworkManagerImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val networkingModule = module {
    singleOf(::UserNetworkManagerImp) { bind<UserNetworkManager>() }
}
