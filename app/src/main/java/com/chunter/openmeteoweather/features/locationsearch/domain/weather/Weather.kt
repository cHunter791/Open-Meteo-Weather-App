package com.chunter.openmeteoweather.features.locationsearch.domain.weather

/**
 * Represents the weather data for the app. Each property is nullable as there are various
 * different properties related to weather. Just because one data is `null` shouldn't invalidate
 * the entire object.
 */
data class Weather(
    val temperature: WeatherValue?,
    val apparentTemperature: WeatherValue?,
    val rain: WeatherValue?,
    val cloudCover: WeatherValue?,
    val windSpeed: WeatherValue?,
    val windDirection: WeatherValue?,
)
