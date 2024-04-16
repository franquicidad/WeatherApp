package com.example.weatherglobantapp.data

import com.example.weatherglobantapp.mocks.Mock
import com.example.weatherglobantapp.ui.home.HomeViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test


class RemoteWeatherListDatasourceImplTest {
    private val apiService = mockk<ApiService>()
    private val viewModel = mockk<HomeViewModel>(relaxed = true)

    @Test
    fun `given the api response when the apiService instance inyected then call the function`() =

        runTest {
            val weatherMock = Mock.listWeather
            val list = apiService.getWeatherItems()
            coEvery { apiService.getWeatherItems() }.returns(list)

            apiService.getWeatherItems()
            assertThat(weatherMock).isEqualTo(list)

        }
}