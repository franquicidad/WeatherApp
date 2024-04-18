package com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation

import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.domain.ForecastListRepository
import com.example.weatherglobantapp.domain.RemoteForecastListDatasource
import javax.inject.Inject

class ForecastListRepositoryImpl @Inject constructor(private val remoteForecastListDatasource: RemoteForecastListDatasource) : ForecastListRepository {
        override suspend fun getForecastListFromRemote(lat: String, lon: String): Forecast? {
        return runCatching {
            remoteForecastListDatasource.getForecastListRemoteDatasource(lat,lon)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}

