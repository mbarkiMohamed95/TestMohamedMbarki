package com.example.domain.di

import com.example.domain.networking.weather.manager.WeatherNetworkManager
import com.example.domain.networking.weather.manager.WeatherNetworkManagerImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val networkingModule = module {
    factoryOf(::WeatherNetworkManagerImp) { bind<WeatherNetworkManager>() }
}
