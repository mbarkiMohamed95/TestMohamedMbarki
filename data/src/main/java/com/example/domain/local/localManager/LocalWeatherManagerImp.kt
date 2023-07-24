package com.example.domain.local.localManager

import com.example.domain.local.dao.weatherDao.WeatherDao
import com.example.domain.local.entitys.WeatherLocalModel
import kotlinx.coroutines.flow.Flow

class LocalWeatherManagerImp  constructor(private val weatherDao: WeatherDao) :
    LocalWeatherManager {
    override suspend fun saveWeather(weatherLocalModel: WeatherLocalModel) {
        weatherDao.insert(weatherLocalModel)
    }

    override suspend fun saveListWeather(weatherLocalModel: List<WeatherLocalModel>) {
        weatherDao.insertList(weatherLocalModel)
    }

    override suspend fun getAllWeathersAsFlow(): Flow<List<WeatherLocalModel>> {
        return weatherDao.loadAllWeathersAsFlow()
    }

    override suspend fun getAllWeathers(): List<WeatherLocalModel> = weatherDao.loadAllWeathers()
    override suspend fun loadWeatherByNameCityFromLocal(cityName: String): WeatherLocalModel =
        weatherDao.loadWeatherByNameCity(cityName)
}