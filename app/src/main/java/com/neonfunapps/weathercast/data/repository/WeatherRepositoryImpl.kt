package com.neonfunapps.weathercast.data.repository

import com.neonfunapps.weathercast.data.mappers.toCityCoordinatesInfo
import com.neonfunapps.weathercast.data.mappers.toCityInfo
import com.neonfunapps.weathercast.data.mappers.toWeatherInfo
import com.neonfunapps.weathercast.data.remote.GeocodingWeatherApi
import com.neonfunapps.weathercast.data.remote.WeatherApi
import com.neonfunapps.weathercast.data.remote.WireFreeThoughtApi
import com.neonfunapps.weathercast.domain.repository.WeatherRepository
import com.neonfunapps.weathercast.domain.util.Resource
import com.neonfunapps.weathercast.domain.weather.CityCoordinatesInfo
import com.neonfunapps.weathercast.domain.weather.SuggestedCityInfo
import com.neonfunapps.weathercast.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val geocodingWeatherApi: GeocodingWeatherApi,
    private val wireFreeThoughtApi: WireFreeThoughtApi,
) : WeatherRepository {

    override suspend fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(
                    latitude = latitude,
                    longitude = longitude,
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error while getting weather data")
        }
    }

    override suspend fun getCityCoordinates(
        city: String,
    ): Resource<CityCoordinatesInfo> {
        return try {
            Resource.Success(
                data = geocodingWeatherApi.getCityCoordinates(city = city).toCityCoordinatesInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error while getting weather data")
        }
    }

    override suspend fun getListOfSuggestedCities(
        cityNamePrefix: String
    ): Resource<List<SuggestedCityInfo>> {
        return try {
            Resource.Success(
                data = wireFreeThoughtApi.getListOfSuggestedCities(cityNamePrefix).toCityInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Error while getting city suggestions")
        }
    }
}
