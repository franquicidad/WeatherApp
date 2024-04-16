package com.example.weatherglobantapp.di

import com.example.weatherglobantapp.data.ApiService
import com.example.weatherglobantapp.data.HttpLandingInterceptor
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.RemoteWeatherListDatasourceImpl
import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.WeatherListRepositoryImpl
import com.example.weatherglobantapp.domain.RemoteWeatherListDatasource
import com.example.weatherglobantapp.domain.WeatherListRepository
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
    fun provideWeatherListRepository(remoteWeatherListDatasource: RemoteWeatherListDatasource): WeatherListRepository {
        return WeatherListRepositoryImpl(remoteWeatherListDatasource)
    }
}