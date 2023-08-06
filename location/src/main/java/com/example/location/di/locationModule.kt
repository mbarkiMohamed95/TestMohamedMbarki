package com.example.location.di

import com.example.data.repository.location.LocationProvider
import com.example.location.manger.LocationManagerImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val locationModule = module {
    singleOf(::LocationManagerImp) { bind<LocationProvider>() }
}