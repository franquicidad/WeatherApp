package com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation

import com.example.weatherglobantapp.data.ApiService
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.domain.RemoteWeatherListDatasource
import javax.inject.Inject

class RemoteWeatherListDatasourceImpl @Inject constructor(private val apiService: ApiService):
    RemoteWeatherListDatasource {
    override suspend fun getWeatherListRemoteDatasource(lat: String, lon: String): Weather? {
        return runCatching {
            apiService.getWeatherItems(lat, lon)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}