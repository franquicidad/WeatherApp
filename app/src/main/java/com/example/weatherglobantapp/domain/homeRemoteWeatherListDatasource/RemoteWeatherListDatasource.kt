package com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource

import com.example.weatherglobantapp.data.model.Weather
import kotlinx.coroutines.Deferred

interface RemoteWeatherListDatasource {
    suspend fun getWeatherListRemoteDatasource(): Deferred<List<Weather>>
}