package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class SuggestedCitiesDto(
    @Json(name = "data")
    val suggestedCitiesData: List<SuggestedCityDto>
)
