package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeApi {

    @GET("search")
    suspend fun forwardGeocode(
        @Query("q") location: String,
        @Query("api_key") apiKey: String,
    ): List<GeocodeResult>
}
