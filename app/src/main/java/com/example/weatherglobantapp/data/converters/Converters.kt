package com.example.weatherglobantapp.data.converters

import android.os.Build
import java.time.Instant
import java.util.Date


class Converters {
    fun convertTimeToClock(dateTimering: Long): Date {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val instant = Instant.ofEpochSecond(dateTimering);
            return Date.from(instant)

        } else {
            val date = Date(dateTimering * 1_000)
            return date
        } // Use the system default time zone

    }

    fun convertDegreeToFarenheigh(temperatureF: Double): Double {
        return ((temperatureF * 9/5) + 32)

    }
}