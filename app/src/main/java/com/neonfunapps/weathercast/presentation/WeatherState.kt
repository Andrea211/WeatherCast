package com.neonfunapps.weathercast.presentation

import com.neonfunapps.weathercast.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
