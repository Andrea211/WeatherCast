package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class HourlyWeatherDataDto(
    @Json(name = "time")
    val time: List<String>,
    @Json(name = "temperature_2m")
    val temperatureList: List<Double>,
    @Json(name = "weathercode")
    val weatherCodeList: List<Int>,
    @Json(name = "pressure_msl")
    val pressureList: List<Double>,
    @Json(name = "windspeed_10m")
    val windSpeedList: List<Double>,
    @Json(name = "relativehumidity_2m")
    val humidityList: List<Double>,
    @Json(name = "apparent_temperature")
    val apparentTemperatureList: List<Double>,
    @Json(name = "rain")
    val rainList: List<Double>,
)
