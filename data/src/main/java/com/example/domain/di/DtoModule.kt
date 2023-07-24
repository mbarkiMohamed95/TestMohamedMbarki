package com.example.domain.di

import com.example.domain.base.DomainDTOMappingService
import com.example.domain.local.entitys.WeatherLocalModel
import com.example.domain.networking.weather.model.WeatherModel
import com.example.domain.repository.weather.model.WeatherRepositoryModel
import com.example.domain.repository.weatherdto.MappingWeatherLocalToRepository
import com.example.domain.repository.weatherdto.MappingWeatherNetWorkToRepository
import com.example.domain.repository.weatherdto.MappingWeatherRemoteToLocal
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val dtoModule = module {
    factoryOf(::MappingWeatherLocalToRepository) { bind<DomainDTOMappingService<WeatherLocalModel, WeatherRepositoryModel>>() }
    factoryOf(::MappingWeatherRemoteToLocal) { bind<DomainDTOMappingService<WeatherModel, WeatherLocalModel>>() }
    factoryOf(::MappingWeatherNetWorkToRepository) { bind<DomainDTOMappingService<WeatherModel, WeatherRepositoryModel>>() }
}