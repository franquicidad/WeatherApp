package com.example.weatherglobantapp.domain

import com.example.weatherglobantapp.dataModel.landing.Weather

interface RemoteWeatherListDatasource {
     suspend fun getWeatherListRemoteDatasource(lat:String,lon:String): Weather?
}