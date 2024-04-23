package com.example.weatherglobantapp.ui.home


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.domain.repository.ForecastListRepository
import com.example.weatherglobantapp.domain.repository.WeatherListRepository
import com.example.weatherglobantapp.mocks.Mock
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    private val forecastListRepository = mockk<ForecastListRepository>(relaxed = true)
    private val weatherListRepository = mockk<WeatherListRepository>(relaxed = true)
    private val viewModel = mockk<HomeViewModel>(relaxed = true)
    private val forecast = mockk<Forecast>(relaxed = true)
    private val mutableFlow = mockk<MutableStateFlow<Forecast>>(relaxed = true)

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Test
    fun `given the weather object when Livedata is creater then getWeather list is called and postValue assigns the weather ob ject`() {
        runTest {

            coEvery {
                weatherListRepository.getWeatherListFromRemote(
                    "1",
                    "1"
                )
            }.returns(Mock.weather)
            val mutableLiveData = MutableLiveData<Weather>()
            mutableLiveData.postValue(weatherListRepository.getWeatherListFromRemote("1","1"))
            viewModel.getWeatherList("1", "1")
            val weather = weatherListRepository.getWeatherListFromRemote("1", "1")
            assertThat(weather).isEqualTo(mutableLiveData.value)
        }
    }
    @Test
    fun `given the weather object when flow is creater then get forecast list is called and value assigns the forecast ob ject`() {
        runTest {

            coEvery {
                forecastListRepository.getForecastListFromRemote(
                    "1",
                    "1"
                )
            }.returns(Mock.forecastMock)
            coEvery {
                mutableFlow.value
            }.returns(Mock.forecastMock)
            viewModel.getWeatherList("1", "1")
            val mutableFlow = mutableFlow.value

            val forecast = forecastListRepository.getForecastListFromRemote("1","1")
            assertEquals(forecast,mutableFlow)
        }
    }
}