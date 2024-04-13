package com.example.weatherglobantapp.di

import com.example.weatherglobantapp.data.ApiService
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.RemoteWeatherListDatasourceImpl
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.WeatherListRepositoryImpl
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.RemoteWeatherListDatasource
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.WeatherListRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    // TODO:Erase / in url

    @Provides
    @Singleton
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather/")
            .build()
            .create(ApiService::class.java)
    }
    @Provides
    fun provideRemoteWeatherList (apiService: ApiService): RemoteWeatherListDatasource{
        return RemoteWeatherListDatasourceImpl(apiService = apiService)
    }
    @Provides
    fun provideWeatherListRepository(remoteWeatherListDatasource: RemoteWeatherListDatasource): WeatherListRepository{
        return WeatherListRepositoryImpl(remoteWeatherListDatasource)
    }

}