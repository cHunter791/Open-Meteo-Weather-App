package com.chunter.openmeteoweather.features.locationsearch.data.weather

import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherValue
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherDataMapperTest {

    private lateinit var objectUnderTest: WeatherDataMapper

    private val defaultUnits: Units = Units(
        temperature2m = "°C",
        apparentTemperature = "°C",
        rain = "mm",
        cloudCover = "%",
        windSpeed10m = "km/h",
        windDirection10m = "°",
    )

    private val defaultWeatherData: WeatherData = WeatherData(
        temperature2m = -4f,
        apparentTemperature = -7.9f,
        rain = 0f,
        cloudCover = 0f,
        windSpeed10m = 7.6f,
        windDirection10m = 267f,
    )

    private val defaultWeather: Weather = Weather(
        temperature = WeatherValue(-4f, "°C"),
        apparentTemperature = WeatherValue(-7.9f, "°C"),
        rain = WeatherValue(0f, "mm"),
        cloudCover = WeatherValue(0f, "%"),
        windSpeed = WeatherValue(7.6f, "km/h"),
        windDirection = WeatherValue(267f, "°"),
    )

    @Before
    fun setup() {
        objectUnderTest = WeatherDataMapper()
    }

    @Test
    fun `mapToDomain given all values returns WeatherData with all values`() {
        val weatherResponse = WeatherResponse(
            currentUnits = defaultUnits,
            current = defaultWeatherData
        )

        val actualResponse = objectUnderTest.mapDataToDomain(weatherResponse)

        assertEquals(defaultWeather, actualResponse)
    }

    @Test
    fun `mapToDomain given some null values returns WeatherData with some null values`() {
        val weatherResponse = WeatherResponse(
            currentUnits = defaultUnits.copy(
                apparentTemperature = null,
                rain = null,
            ),
            current = defaultWeatherData.copy(
                apparentTemperature = null,
                cloudCover = null
            )
        )

        val actualResponse = objectUnderTest.mapDataToDomain(weatherResponse)

        assertEquals(
            defaultWeather.copy(
                apparentTemperature = null,
                rain = null,
                cloudCover = null,
            ),
            actualResponse
        )
    }
}