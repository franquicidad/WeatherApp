package com.example.weatherglobantapp.domain

import com.example.weatherglobantapp.dataModel.landing.Weather

interface WeatherListRepository {
    suspend fun getWeatherListFromRemote(lat:String,lon:String): Weather?
}