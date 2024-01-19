package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeApi {

    /**
     * [GET] endpoint call to the geocode API. The [Query] parameter "q" represents
     * the location query you are decoding. The [Query] parameter "api_key" is
     * required to access the API.
     */
    @GET("search")
    suspend fun forwardGeocode(
        @Query("q") location: String,
        @Query("api_key") apiKey: String,
    ): List<GeocodeResponse>
}
