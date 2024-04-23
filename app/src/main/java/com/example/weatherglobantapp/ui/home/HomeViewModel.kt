package com.example.weatherglobantapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.forecast.Forecast
import com.example.weatherglobantapp.domain.repository.ForecastListRepository
import com.example.weatherglobantapp.domain.repository.WeatherListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: WeatherListRepository, private val forecastListRepository: ForecastListRepository) : ViewModel() {

    private val _weather = MutableLiveData<Weather?>(null)
    val weather: LiveData<Weather?> = _weather

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast: StateFlow<Forecast?> = _forecast

    fun getWeatherList(lat:String, long:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weather.postValue(repository.getWeatherListFromRemote(lat,long))
            _forecast.value = forecastListRepository.getForecastListFromRemote(lat,long)
        }
    }
}

