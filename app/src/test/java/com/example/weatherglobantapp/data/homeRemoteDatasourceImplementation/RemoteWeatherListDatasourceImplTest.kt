package com.example.weatherglobantapp.data.homeRemoteDatasourceImplementation

import com.example.weatherglobantapp.data.api.ApiService
import com.example.weatherglobantapp.domain.remote.RemoteWeatherListDatasource
import com.example.weatherglobantapp.mocks.Mock
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class RemoteWeatherListDatasourceImplTest {
    private val apiService = mockk<ApiService>(relaxed = true)
    private val remoteWeatherListDatasource = mockk<RemoteWeatherListDatasource>(relaxed = true)

    @Test
    fun `given the api response when the apiService instance inyected then call the function`() =
        runTest {
            val weatherMock = Mock.weather
            coEvery { apiService.getWeatherItems("1", "1", "1") }.returns(weatherMock)
            coEvery {
                remoteWeatherListDatasource.getWeatherListRemoteDatasource(
                    "1",
                    "1"
                )
            }.returns(weatherMock)
            val list = apiService.getWeatherItems("1", "1", "1")

            remoteWeatherListDatasource.getWeatherListRemoteDatasource("1", "1")
            assertThat(weatherMock).isEqualTo(list)
            assertThat(list).isNotNull()
        }
}