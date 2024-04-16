package com.example.weatherglobantapp.domain

import com.example.weatherglobantapp.dataModel.landing.Weather

interface ForecastListRepository {
    suspend fun getForecastListFromRemote(lat:String, lon:String): Weather?
}