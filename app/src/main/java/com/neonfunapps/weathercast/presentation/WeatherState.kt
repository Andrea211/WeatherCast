package com.neonfunapps.weathercast.presentation

import com.neonfunapps.weathercast.domain.weather.WeatherInfo

data class WeatherState(
    val city: String? = null,
    val cityCoordinates: Pair<Double, Double>? = null,
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val listOfSuggestedCities: List<Triple<String, String, String>>? = null
)
