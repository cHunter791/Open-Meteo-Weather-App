package com.chunter.openmeteoweather.features.locationsearch.domain.weather

import com.chunter.openmeteoweather.core.logger.Level
import com.chunter.openmeteoweather.core.logger.Logger
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.GeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFoundException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetWeatherForLocationUseCaseTest {

    @MockK(relaxUnitFun = true)
    private lateinit var logger: Logger

    @MockK
    private lateinit var geocodeRepository: GeocodeRepository

    @MockK
    private lateinit var weatherRepository: WeatherRepository

    private val location = "Test Location"

    private lateinit var objectUnderTest: GetWeatherForLocationUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        objectUnderTest = GetWeatherForLocationUseCase(
            logger,
            geocodeRepository,
            weatherRepository,
        )
    }

    @Test
    fun `invoke use case when success then weather data returned`() = runTest {
        val latLng = mockk<LatLng>()
        val weather = mockk<Weather>()
        coEvery { geocodeRepository.forwardGeocode(location) } returns latLng
        coEvery { weatherRepository.getWeatherData(latLng) } returns weather

        val actualResult = objectUnderTest(location)

        assertEquals(weather, actualResult)
    }

    @Test(expected = NoGeocodeResultFoundException::class)
    fun `invoke use case when NoGeocodeResultFoundException thrown then error logged`() = runTest {
        val errorMessage = "No Geocode found"
        val noGeocodeResultFoundException = NoGeocodeResultFoundException(errorMessage)
        coEvery { geocodeRepository.forwardGeocode(location) } throws noGeocodeResultFoundException

        objectUnderTest(location)

        verify { logger.log(Level.Error, errorMessage, noGeocodeResultFoundException) }
    }

    @Test(expected = Exception::class)
    fun `invoke use case when Exception thrown then error logged`() = runTest {
        val exception = Exception()
        coEvery { geocodeRepository.forwardGeocode(location) } throws exception

        objectUnderTest(location)

        verify {
            logger.log(
                Level.Error,
                "Unknown error occurred retrieving weather data",
                exception,
            )
        }
    }
}