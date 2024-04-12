package com.example.weatherglobantapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherglobantapp.data.model.Weather
import com.example.weatherglobantapp.domain.homeRemoteWeatherListDatasource.WeatherListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: WeatherListRepository) : ViewModel() {

    private val _weather = MutableStateFlow<List<Weather>?>(null)
    val weather: MutableStateFlow<List<Weather>?> = _weather

    fun getWeatherList(){
        viewModelScope.async {
            _weather.value = repository.getWeatherListFromRemote()?.await()
        }
    }
}