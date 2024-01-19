package com.chunter.openmeteoweather.features.locationsearch.domain.weather

data class Weather(
    val temperature: WeatherValue?,
    val apparentTemperature: WeatherValue?,
    val rain: WeatherValue?,
    val cloudCover: WeatherValue?,
    val windSpeed: WeatherValue?,
    val windDirection: WeatherValue?,
)
