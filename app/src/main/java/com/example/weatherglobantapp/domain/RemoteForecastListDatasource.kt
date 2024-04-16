package com.example.weatherglobantapp.domain

import com.example.weatherglobantapp.dataModel.landing.Weather

interface RemoteForecastListDatasource {
     suspend fun getForecastListRemoteDatasource(lat:String, lon:String): Weather?
}