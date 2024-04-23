package com.example.weatherglobantapp.domain.remote

import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast

interface RemoteForecastListDatasource {
     suspend fun getForecastListRemoteDatasource(lat:String, lon:String): Forecast?
}