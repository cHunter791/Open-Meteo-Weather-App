package com.chunter.openmeteoweather.features.locationsearch.domain.weather

/**
 * A simple class to represent a single weather data. Each bit of weather data will have
 * a value and a unit to represent it.
 */
data class WeatherValue(
    val value: Float,
    val unit: String,
)
