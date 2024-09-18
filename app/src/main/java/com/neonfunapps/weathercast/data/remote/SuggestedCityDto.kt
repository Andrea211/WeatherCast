package com.neonfunapps.weathercast.data.remote

import com.squareup.moshi.Json

data class SuggestedCityDto(
    @Json(name = "name")
    val name: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "region")
    val region: String
)
