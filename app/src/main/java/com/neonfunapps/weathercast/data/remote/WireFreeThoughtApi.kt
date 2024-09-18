package com.neonfunapps.weathercast.data.remote

import com.neonfunapps.weathercast.BuildConfig.WireFreeThought_API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WireFreeThoughtApi {

    @GET("v1/geo/cities?sort=-population&limit=10")
    @Headers("X-RapidAPI-Key: $WireFreeThought_API_KEY")
    suspend fun getListOfSuggestedCities(
        @Query("namePrefix") namePrefix: String,
    ): SuggestedCitiesDto

}
