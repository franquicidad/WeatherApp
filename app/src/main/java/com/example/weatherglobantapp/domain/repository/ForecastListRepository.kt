package com.example.weatherglobantapp.domain.repository

import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast

interface ForecastListRepository {
    suspend fun getForecastListFromRemote(lat:String, lon:String): Forecast?
}