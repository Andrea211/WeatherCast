package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class CityCoordinatesDto(
    @Json(name = "results")
    val results: List<CityResultDto>
)
