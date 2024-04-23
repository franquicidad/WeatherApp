package com.example.weatherglobantapp.data.api

import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object API_KEY {
        const val API = "8027ed96953ca9aa9b376b897799acca"
    }

    @GET("weather")
    suspend fun getWeatherItems(
        @Query("appId") apiKey:String = API,
        @Query("lat") lat:String,
        @Query("lon") lon:String,
    ) : Weather

    @GET("forecast")
    suspend fun getForecastItems(
        @Query("appId") apiKey: String = API,
        @Query("lat") lat:String,
        @Query("lon") lon:String,
    ) : Forecast
}

