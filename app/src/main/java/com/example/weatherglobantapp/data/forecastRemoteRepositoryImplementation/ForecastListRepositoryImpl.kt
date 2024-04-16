package com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation

import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.domain.RemoteForecastListDatasource
import com.example.weatherglobantapp.domain.WeatherListRepository
import javax.inject.Inject

class ForecastListRepositoryImpl @Inject constructor(private val remoteForecastListDatasource: RemoteForecastListDatasourceImpl) :
    RemoteForecastListDatasource {
    override suspend fun getForecastListRemoteDatasource(lat: String, lon: String): Weather? {
        return runCatching {
            remoteForecastListDatasource.getForecastListRemoteDatasource(lat,lon)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}

