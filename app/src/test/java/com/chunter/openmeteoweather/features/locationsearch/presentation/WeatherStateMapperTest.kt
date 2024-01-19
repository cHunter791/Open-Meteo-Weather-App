package com.chunter.openmeteoweather.features.locationsearch.presentation

import android.content.res.Resources
import com.chunter.openmeteoweather.R
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherValue
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchViewModel.WeatherResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WeatherStateMapperTest {

    @MockK
    private lateinit var resources: Resources

    private val temperatureTitle = "Temperature"
    private val apparentTemperatureTitle = "Apparent Temperature"
    private val rainTitle = "Rain"
    private val cloudCoverTitle = "Cloud Cover"
    private val windSpeedTitle = "Wind Speed"
    private val windDirectionTitle = "Wind Direction"

    private val defaultWeather: Weather = Weather(
        temperature = WeatherValue(-4f, "°C"),
        apparentTemperature = WeatherValue(-7.9f, "°C"),
        rain = WeatherValue(0f, "mm"),
        cloudCover = WeatherValue(0f, "%"),
        windSpeed = WeatherValue(7.6f, "km/h"),
        windDirection = WeatherValue(267f, "°"),
    )

    private lateinit var objectUnderTest: WeatherStateMapper

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        objectUnderTest = WeatherStateMapper(resources)

        every { resources.getString(R.string.title_temperature) } returns temperatureTitle
        every { resources.getString(R.string.title_apparent_temperature) } returns apparentTemperatureTitle
        every { resources.getString(R.string.title_rain) } returns rainTitle
        every { resources.getString(R.string.title_cloud_cover) } returns cloudCoverTitle
        every { resources.getString(R.string.title_wind_speed) } returns windSpeedTitle
        every { resources.getString(R.string.title_wind_direction) } returns windDirectionTitle
    }

    @Test
    fun `mapDomainToState given all values then all values returned as results`() {
        val weatherResults = objectUnderTest.mapDomainToState(defaultWeather)

        assertEquals(6, weatherResults.size)
        assertTrue(
            weatherResults.contains(WeatherResult(temperatureTitle, "-4.0°C"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(apparentTemperatureTitle, "-7.9°C"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(rainTitle, "0.0mm"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(cloudCoverTitle, "0.0%"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(windSpeedTitle, "7.6km/h"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(windDirectionTitle, "267.0°"))
        )
    }

    @Test
    fun `mapDomainToState given some null values then only non-null values returned as results`() {
        val weather = defaultWeather.copy(
            rain = null,
            cloudCover = null,
        )
        val weatherResults = objectUnderTest.mapDomainToState(weather)

        assertEquals(4, weatherResults.size)
        assertTrue(
            weatherResults.contains(WeatherResult(temperatureTitle, "-4.0°C"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(apparentTemperatureTitle, "-7.9°C"))
        )
        assertFalse(
            weatherResults.contains(WeatherResult(rainTitle, "0.0mm"))
        )
        assertFalse(
            weatherResults.contains(WeatherResult(cloudCoverTitle, "0.0%"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(windSpeedTitle, "7.6km/h"))
        )
        assertTrue(
            weatherResults.contains(WeatherResult(windDirectionTitle, "267.0°"))
        )
    }
}