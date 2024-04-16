package com.example.weatherglobantapp.data

import com.example.weatherglobantapp.dataModel.landing.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object API_KEY {
        const val API_KEY = "8027ed96953ca9aa9b376b897799acca"
    }

    @GET("weather")
    suspend fun getWeatherItems(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
    ) : Weather

    @GET("forecast")
    suspend fun getForecastItems(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
    ) : Weather
}

