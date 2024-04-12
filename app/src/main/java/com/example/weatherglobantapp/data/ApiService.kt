package com.example.weatherglobantapp.data

import retrofit2.http.GET
import retrofit2.http.Query
interface ApiService {
    companion object API_KEY{
            const val API_KEY ="fb49a239d7e734d19560c5efb43e1ff2"
    }
    @GET()
    suspend fun getWeatherItems(
        @Query("appid") appId: String = API_KEY.API_KEY,
        @Query("lat") lat:Int,
        @Query("long") long:Int,
    ){

    }
}

