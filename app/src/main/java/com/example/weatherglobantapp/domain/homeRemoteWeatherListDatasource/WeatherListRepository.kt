package com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource

import com.example.weatherglobantapp.data.model.Weather
import kotlinx.coroutines.Deferred

interface WeatherListRepository {
    suspend fun getWeatherListFromRemote(): Deferred<List<Weather>>?
}