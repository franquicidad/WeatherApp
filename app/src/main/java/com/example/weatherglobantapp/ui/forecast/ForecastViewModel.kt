package com.example.weatherglobantapp.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForecastViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This Hola"
    }
    val text: LiveData<String> = _text
}