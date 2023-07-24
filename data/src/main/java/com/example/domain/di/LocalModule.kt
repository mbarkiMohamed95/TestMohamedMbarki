package com.example.domain.di

import com.example.domain.local.localManager.LocalWeatherManager
import com.example.domain.local.localManager.LocalWeatherManagerImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


private val localModule = module {
    factoryOf(::LocalWeatherManagerImp) { bind<LocalWeatherManager>() }
}