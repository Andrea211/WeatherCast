package com.neonfunapps.weathercast.data.mappers

import com.neonfunapps.weathercast.domain.weather.WeatherData
import com.neonfunapps.weathercast.data.remote.HourlyWeatherDataDto
import com.neonfunapps.weathercast.data.remote.WeatherDto
import com.neonfunapps.weathercast.domain.weather.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData,
)

fun HourlyWeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        IndexedWeatherData(
            index = index, data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperatureList[index],
                pressure = pressureList[index],
                windSpeed = windSpeedList[index],
                humidity = humidityList[index],
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { weatherData -> weatherData.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap.getOrElse(0) { emptyList() }.find { it.time == now }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
    )
}
