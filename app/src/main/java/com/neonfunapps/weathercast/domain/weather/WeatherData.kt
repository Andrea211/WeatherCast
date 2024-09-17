package com.neonfunapps.weathercast.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val formattedDate: String,
    val temperatureCelsius: String,
    val pressure: Double,
    val windSpeed: String,
    val humidity: String,
    val apparentTemperature: String,
    val rain: String,
    val weatherType: WeatherType,
)
