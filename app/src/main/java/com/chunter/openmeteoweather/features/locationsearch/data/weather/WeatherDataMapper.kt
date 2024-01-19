package com.chunter.openmeteoweather.features.locationsearch.data.weather

import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherValue
import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapDataToDomain(weatherResponse: WeatherResponse): Weather {
        return Weather(
            temperature = createWeatherValueFromCurrent(
                weatherResponse.current.temperature2m,
                weatherResponse.currentUnits.temperature2m,
            ),
            apparentTemperature = createWeatherValueFromCurrent(
                weatherResponse.current.apparentTemperature,
                weatherResponse.currentUnits.apparentTemperature,
            ),
            rain = createWeatherValueFromCurrent(
                weatherResponse.current.rain,
                weatherResponse.currentUnits.rain,
            ),
            cloudCover = createWeatherValueFromCurrent(
                weatherResponse.current.cloudCover,
                weatherResponse.currentUnits.cloudCover,
            ),
            windSpeed = createWeatherValueFromCurrent(
                weatherResponse.current.windSpeed10m,
                weatherResponse.currentUnits.windSpeed10m,
            ),
            windDirection = createWeatherValueFromCurrent(
                weatherResponse.current.windDirection10m,
                weatherResponse.currentUnits.windDirection10m,
            ),
        )
    }

    private fun createWeatherValueFromCurrent(
        currentValue: Float?,
        currentUnit: String?,
    ): WeatherValue? {
        if (currentValue == null || currentUnit == null) return null
        return WeatherValue(currentValue, currentUnit)
    }
}