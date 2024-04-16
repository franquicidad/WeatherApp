package com.example.weatherglobantapp.dataModel.landing.forecast

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Detail>,
    val message: Int
)