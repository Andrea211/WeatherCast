package com.neonfunapps.weathercast.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingWeatherApi {

    @GET("v1/search?count=1&language=en")
    suspend fun getCityCoordinates(
        @Query("name") city: String,
    ): CityCoordinatesDto

}
