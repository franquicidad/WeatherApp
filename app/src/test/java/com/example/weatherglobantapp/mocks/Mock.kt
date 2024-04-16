package com.example.weatherglobantapp.mocks

import com.example.weatherglobantapp.dataModel.landing.Clouds
import com.example.weatherglobantapp.dataModel.landing.Coord
import com.example.weatherglobantapp.dataModel.landing.Main
import com.example.weatherglobantapp.dataModel.landing.Rain
import com.example.weatherglobantapp.dataModel.landing.Sys
import com.example.weatherglobantapp.dataModel.landing.Weather
import com.example.weatherglobantapp.dataModel.landing.Wind

object Mock {
val weather =  Weather(base = "", clouds = Clouds(5), cod = 5, coord = Coord(1.0,2.0), dt = 4, main = Main(feels_like = 2.0, grnd_level = 4, humidity = 3, pressure = 4, sea_level = 4, temp = 2.0, temp_max = 2.0, temp_min = 2.0), id = 2, name = "", rain = Rain(2.0), sys = Sys(country = "", id = 3, sunrise = 1, sunset = 2, type = 1), timezone = 3, visibility = 3, weather = listOf(

), wind = Wind(deg = 2, gust = 2.4, speed = 2.5)
)
    val listWeather = listOf<Weather>(
        weather
    )
}