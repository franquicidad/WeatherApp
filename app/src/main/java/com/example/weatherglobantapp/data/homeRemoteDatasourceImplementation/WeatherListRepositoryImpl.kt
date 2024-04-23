package com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation

import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.domain.remote.RemoteWeatherListDatasource
import com.example.weatherglobantapp.domain.repository.WeatherListRepository
import javax.inject.Inject

class WeatherListRepositoryImpl @Inject constructor(private val remoteWeatherListDatasource: RemoteWeatherListDatasource) :
    WeatherListRepository {
    override suspend fun getWeatherListFromRemote(lat: String, lon: String): Weather? {
        return runCatching {
            remoteWeatherListDatasource.getWeatherListRemoteDatasource(lat,lon)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}

