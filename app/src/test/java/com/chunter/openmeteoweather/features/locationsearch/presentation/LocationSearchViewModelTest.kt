package com.chunter.openmeteoweather.features.locationsearch.presentation

import com.chunter.openmeteoweather.features.locationsearch.domain.weather.GetWeatherForLocationUseCase
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchViewModel.State
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationSearchViewModelTest {

    @MockK
    private lateinit var getWeatherForLocationUseCase: GetWeatherForLocationUseCase

    @MockK
    private lateinit var weatherStateMapper: WeatherStateMapper

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var objectUnderTest: LocationSearchViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        objectUnderTest = LocationSearchViewModel(
            getWeatherForLocationUseCase,
            weatherStateMapper,
            testDispatcher,
        )
    }

    @Test
    fun `Perform Search action when successful updates state with results`() = runTest {
        val location = "Test Location"
        val weather = mockk<Weather>()
        val weatherResults = mockk<List<LocationSearchViewModel.WeatherResult>>(relaxed = true)
        coEvery { getWeatherForLocationUseCase(location) } returns weather
        every { weatherStateMapper.mapDomainToState(weather) } returns weatherResults

        val results = mutableListOf<State>()
        val job = launch(testDispatcher) {
            objectUnderTest.state.toList(results)
        }

        objectUnderTest.handleAction(LocationSearchViewModel.Action.SearchSubmitted(location))

        assertEquals(State(), results[0])
        assertEquals(
            State(
                isLoading = true,
                location = location,
                weatherResults = emptyList(),
                errorMessage = null,
            ), results[1]
        )
        assertEquals(
            State(
                isLoading = false,
                location = location,
                weatherResults = weatherResults,
            ),
            results[2]
        )

        job.cancel()
    }

    @Test
    fun `Perform Search action when unsuccessful updates state with empty results`() = runTest {
        val location = "Test Location"
        val exception = Exception()
        val errorMessage = "Error Message"
        coEvery { getWeatherForLocationUseCase(location) } throws exception
        every { weatherStateMapper.mapExceptionToErrorMessage(exception) } returns errorMessage

        val results = mutableListOf<State>()
        val job = launch(testDispatcher) {
            objectUnderTest.state.toList(results)
        }

        objectUnderTest.handleAction(LocationSearchViewModel.Action.SearchSubmitted(location))

        assertEquals(State(), results[0])
        assertEquals(
            State(
                isLoading = true,
                location = location,
                weatherResults = emptyList(),
                errorMessage = null,
            ), results[1]
        )
        assertEquals(
            State(
                isLoading = false,
                location = location,
                weatherResults = emptyList(),
                errorMessage = errorMessage,
            ),
            results[2]
        )

        job.cancel()
    }
}