package com.neonfunapps.weathercast.data.repository

import com.neonfunapps.weathercast.data.mappers.toWeatherInfo
import com.neonfunapps.weathercast.data.remote.WeatherApi
import com.neonfunapps.weathercast.domain.repository.WeatherRepository
import com.neonfunapps.weathercast.domain.util.Resource
import com.neonfunapps.weathercast.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latitude = latitude,
                    longitude = longitude,
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error while getting weather data")
        }
    }
}
