package com.neonfunapps.weathercast.domain.repository

import com.neonfunapps.weathercast.domain.util.Resource
import com.neonfunapps.weathercast.domain.weather.CityCoordinatesInfo
import com.neonfunapps.weathercast.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>

    suspend fun getCityCoordinates(city:String): Resource<CityCoordinatesInfo>
}
