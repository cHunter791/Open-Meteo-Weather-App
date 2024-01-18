package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFound
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteGeocodeRepositoryTest {

    @MockK
    private lateinit var geocodeApi: GeocodeApi

    @MockK
    private lateinit var geocodeApiKeyProvider: GeocodeApiKeyProvider

    private lateinit var objectUnderTest: RemoteGeocodeRepository

    private val location = "Test Location"
    private val apiKey = "api_key"

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        objectUnderTest = RemoteGeocodeRepository(geocodeApi, geocodeApiKeyProvider)

        every { geocodeApiKeyProvider.key } returns apiKey
    }

    @Test
    fun `forwardGeocode when results returned then first value returned`() = runTest {
        val geocodeResults: List<GeocodeResult> = listOf(
            GeocodeResult(
                latitude = 1.0,
                longitude = -1.0,
            ),
            GeocodeResult(
                latitude = 2.0,
                longitude = -2.0,
            )
        )

        coEvery { geocodeApi.forwardGeocode(location, apiKey) } returns geocodeResults

        val actualResult = objectUnderTest.forwardGeocode(location)

        assertEquals(LatLng(1.0, -1.0), actualResult)
    }

    @Test(expected = NoGeocodeResultFound::class)
    fun `forwardGeocode when no results returned then exception thrown`() = runTest {
        val geocodeResults: List<GeocodeResult> = listOf()

        coEvery { geocodeApi.forwardGeocode(location, apiKey) } returns geocodeResults

        objectUnderTest.forwardGeocode(location)
    }

    @Test
    fun `forwardGeocode when results returned with null properties then non-null value returned`() =
        runTest {
            val geocodeResults: List<GeocodeResult> = listOf(
                GeocodeResult(
                    latitude = null,
                    longitude = -1.0,
                ),
                GeocodeResult(
                    latitude = 2.0,
                    longitude = null,
                ),
                GeocodeResult(
                    latitude = 3.0,
                    longitude = -3.0,
                )
            )

            coEvery { geocodeApi.forwardGeocode(location, apiKey) } returns geocodeResults

            val actualResult = objectUnderTest.forwardGeocode(location)

            assertEquals(LatLng(3.0, -3.0), actualResult)
        }

    @Test(expected = NoGeocodeResultFound::class)
    fun `forwardGeocode when results returned with all null properties then exception thrown`() =
        runTest {
            val geocodeResults: List<GeocodeResult> = listOf(
                GeocodeResult(
                    latitude = null,
                    longitude = null,
                ),
                GeocodeResult(
                    latitude = null,
                    longitude = null,
                )
            )

            coEvery { geocodeApi.forwardGeocode(location, apiKey) } returns geocodeResults

            objectUnderTest.forwardGeocode(location)
        }
}