package com.chunter.openmeteoweather.features.locationsearch.data.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") currentParameters: String,
    ): WeatherResponse
}