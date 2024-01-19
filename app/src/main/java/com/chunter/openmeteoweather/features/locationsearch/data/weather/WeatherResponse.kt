package com.chunter.openmeteoweather.features.locationsearch.data.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_units")
    val currentUnits: Units,
    @SerializedName("current")
    val current: WeatherData,
)

data class Units(
    @SerializedName("temperature_2m")
    val temperature2m: String?,
    @SerializedName("apparent_temperature")
    val apparentTemperature: String?,
    @SerializedName("rain")
    val rain: String?,
    @SerializedName("cloud_cover")
    val cloudCover: String?,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: String?,
    @SerializedName("wind_direction_10m")
    val windDirection10m: String?,
)

data class WeatherData(
    @SerializedName("temperature_2m")
    val temperature2m: Float?,
    @SerializedName("apparent_temperature")
    val apparentTemperature: Float?,
    @SerializedName("rain")
    val rain: Float?,
    @SerializedName("cloud_cover")
    val cloudCover: Float?,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: Float?,
    @SerializedName("wind_direction_10m")
    val windDirection10m: Float?,
)
