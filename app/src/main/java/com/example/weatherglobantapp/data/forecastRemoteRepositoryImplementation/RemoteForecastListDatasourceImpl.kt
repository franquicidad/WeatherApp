package com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation

import com.example.weatherglobantapp.data.api.ApiService
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.domain.remote.RemoteForecastListDatasource
import javax.inject.Inject

class RemoteForecastListDatasourceImpl @Inject constructor(private val apiService: ApiService):
    RemoteForecastListDatasource {
       override suspend fun getForecastListRemoteDatasource(lat: String, lon: String): Forecast? {
        return runCatching {
            val apiResponse = apiService.getForecastItems(lat =lat, lon = lon)
            println(apiResponse)
            return apiResponse
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}