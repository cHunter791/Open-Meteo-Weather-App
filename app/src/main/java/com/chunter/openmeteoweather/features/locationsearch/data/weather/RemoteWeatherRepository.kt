package com.chunter.openmeteoweather.features.locationsearch.data.weather

import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherRepository
import javax.inject.Inject

class RemoteWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDataMapper: WeatherDataMapper,
) : WeatherRepository {

    override suspend fun getWeatherData(latLng: LatLng): Weather {
        val weatherResponse = weatherApi.getWeather(
            latitude = latLng.latitude,
            longitude = latLng.longitude,
            currentParameters = currentParameters,
        )
        return weatherDataMapper.mapDataToDomain(weatherResponse)
    }

    companion object {

        private const val currentParameters =
            "temperature_2m,apparent_temperature,rain,cloud_cover,wind_speed_10m,wind_direction_10m"
    }
}