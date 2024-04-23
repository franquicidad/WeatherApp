package com.example.weatherglobantapp.data

import com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation.WeatherListRepositoryImpl
import com.example.weatherglobantapp.domain.remote.RemoteWeatherListDatasource
import com.example.weatherglobantapp.mocks.Mock
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WeatherListRepositoryImplTest {
    private val remoteWeatherListDatasource = mockk<RemoteWeatherListDatasource>(relaxed = true)
    private val repositoryWeather  = mockk<WeatherListRepositoryImpl>(relaxed = true)

    @Test
    fun `given the list when the function is called in the remote Datasource then it returns a list`() {
        runTest {
            val weatherMock = Mock.weather
            coEvery { remoteWeatherListDatasource.getWeatherListRemoteDatasource("1","1") }.returns(weatherMock)
            coEvery { repositoryWeather.getWeatherListFromRemote("1","1") }.returns(weatherMock)

            val list = repositoryWeather.getWeatherListFromRemote("1","1")
            Truth.assertThat(weatherMock).isEqualTo(list)
            Truth.assertThat(list).isNotNull()
        }
    }
}