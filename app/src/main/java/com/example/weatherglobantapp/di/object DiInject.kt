package com.example.weatherglobantapp.di

import com.example.weatherglobantapp.data.api.ApiService
import com.example.weatherglobantapp.data.api.HttpLandingInterceptor
import com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation.ForecastListRepositoryImpl
import com.example.weatherglobantapp.data.forecastRemoteRepositoryImplementation.RemoteForecastListDatasourceImpl
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.RemoteWeatherListDatasourceImpl
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.WeatherListRepositoryImpl
import com.example.weatherglobantapp.domain.repository.ForecastListRepository
import com.example.weatherglobantapp.domain.remote.RemoteForecastListDatasource
import com.example.weatherglobantapp.domain.remote.RemoteWeatherListDatasource
import com.example.weatherglobantapp.domain.repository.WeatherListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideAnalyticsService(
        // Potential dependencies of this type
    ): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLandingInterceptor())
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRemoteWeatherList(apiService: ApiService): RemoteWeatherListDatasource {
        return RemoteWeatherListDatasourceImpl(apiService = apiService)
    }
    @Provides
    fun provideRemoteForecastDatasource (apiService: ApiService): RemoteForecastListDatasource {
        return RemoteForecastListDatasourceImpl(apiService)
    }

    @Provides
    fun provideWeatherListRepository(remoteWeatherListDatasource: RemoteWeatherListDatasource): WeatherListRepository {
        return WeatherListRepositoryImpl(remoteWeatherListDatasource)
    }
    @Provides
    fun provideRemoteListRepository( remoteForecastListDatasource: RemoteForecastListDatasource): ForecastListRepository {
        return ForecastListRepositoryImpl(remoteForecastListDatasource)
    }
}