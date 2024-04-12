package com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation

import com.example.weatherglobantapp.data.ApiService
import com.example.weatherglobantapp.data.model.Weather
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.RemoteWeatherListDatasource
import kotlinx.coroutines.Deferred

class RemoteWeatherListDatasourceImpl (private val apiService: ApiService): RemoteWeatherListDatasource {
    override suspend fun getWeatherListRemoteDatasource(): Deferred<List<Weather>> =
         apiService.getWeatherItems(ApiService.API_KEY,0,0)

}