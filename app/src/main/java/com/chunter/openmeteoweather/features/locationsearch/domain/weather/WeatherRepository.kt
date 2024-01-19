package com.chunter.openmeteoweather.features.locationsearch.domain.weather

import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng

interface WeatherRepository {

    suspend fun getWeatherData(latLng: LatLng): Weather
}