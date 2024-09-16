package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class CityResultDto(
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "longitude")
    val longitude: Double
)
