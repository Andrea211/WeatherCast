package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @Json(name = "hourly")
    val weatherData: HourlyWeatherDataDto
)
