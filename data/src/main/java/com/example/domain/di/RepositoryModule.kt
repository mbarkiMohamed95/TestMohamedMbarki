package com.example.domain.di

import com.example.domain.repository.weather.manager.WeatherRepository
import com.example.domain.repository.weather.manager.WeatherRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val repositoryModule = module {
    factoryOf(::WeatherRepositoryImp) { bind <WeatherRepository> () }
}
