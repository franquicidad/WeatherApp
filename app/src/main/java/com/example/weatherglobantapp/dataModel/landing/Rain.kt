package com.example.weatherglobantapp.dataModel.landing

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")val oneHour: Double
)