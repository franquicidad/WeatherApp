package com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation

import com.example.weatherglobantapp.data.ApiService
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.domain.RemoteForecastListDatasource
import com.example.weatherglobantapp.domain.RemoteWeatherListDatasource
import javax.inject.Inject

class RemoteForecastListDatasourceImpl @Inject constructor(private val apiService: ApiService):
    RemoteForecastListDatasource {
       override suspend fun getForecastListRemoteDatasource(lat: String, lon: String): Weather? {
        return runCatching {
            apiService.getForecastItems(lat, lon)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}