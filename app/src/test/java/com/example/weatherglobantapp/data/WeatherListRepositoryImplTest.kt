package com.example.weatherglobantapp.data

import com.example.weatherglobantapp.domain.RemoteWeatherListDatasource
import com.example.weatherglobantapp.mocks.Mock
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class WeatherListRepositoryImplTest {
    private val remoteWeatherListDatasource = mockk<RemoteWeatherListDatasource>()

    @Test
    fun `given the list when the function is called in the remote Datasource then it returns a list`() {
        runTest {
            val weatherMock = Mock.listWeather
            val list = remoteWeatherListDatasource.getWeatherListRemoteDatasource(,)
            coEvery { remoteWeatherListDatasource.getWeatherListRemoteDatasource(,) }.returns(list)
            remoteWeatherListDatasource.getWeatherListRemoteDatasource(,)
            Truth.assertThat(weatherMock).isEqualTo(list)

        }

    }

}