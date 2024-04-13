package com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation

import com.example.weatherglobantapp.data.model.Weather
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.RemoteWeatherListDatasource
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.WeatherListRepository
import kotlinx.coroutines.Deferred
import java.lang.Exception
import javax.inject.Inject

class WeatherListRepositoryImpl @Inject constructor(private val remoteWeatherListDatasource: RemoteWeatherListDatasource) : WeatherListRepository{
    override suspend fun getWeatherListFromRemote(): Deferred<List<Weather>>? {
        try {
            return remoteWeatherListDatasource.getWeatherListRemoteDatasource()
        } catch (e: Exception){
            println("--------------------------->$e")
        }
        return null
    }

}