package com.chunter.openmeteoweather.features.locationsearch.data.weather

import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteWeatherRepositoryTest {

    @MockK
    private lateinit var weatherApi: WeatherApi

    @MockK
    private lateinit var weatherDataMapper: WeatherDataMapper

    private lateinit var objectUnderTest: RemoteWeatherRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        objectUnderTest = RemoteWeatherRepository(weatherApi, weatherDataMapper)
    }

    @Test
    fun `getWeatherData when successful then returns weather data`() = runTest {
        val latLng = LatLng(latitude = 1.0, longitude = 1.0)
        val weatherResponse = mockk<WeatherResponse>()
        val weather = mockk<Weather>()

        coEvery {
            weatherApi.getWeather(
                latLng.latitude,
                latLng.longitude,
                any()
            )
        } returns weatherResponse
        every { weatherDataMapper.mapDataToDomain(weatherResponse) } returns weather

        val actualResponse = objectUnderTest.getWeatherData(latLng)

        assertEquals(weather, actualResponse)
    }
}