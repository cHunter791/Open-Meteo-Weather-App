package com.chunter.openmeteoweather.features.locationsearch.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.core.ui.theme.LocalDimensions
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchViewModel

@Composable
fun WeatherList(
    modifier: Modifier = Modifier,
    weatherResults: List<LocationSearchViewModel.WeatherResult> = emptyList(),
) {
    val weatherCardGridSpacing = LocalDimensions.current.weatherCardDimensions.gridSpacing

    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = weatherCardGridSpacing,
        horizontalArrangement = Arrangement.spacedBy(weatherCardGridSpacing),
    ) {
        items(weatherResults) { weatherResult ->
            WeatherCard(weatherResult = weatherResult)
        }
    }
}

@Preview
@Composable
private fun WeatherListPreview() {
    OpenMeteoWeatherTheme {
        WeatherList(
            weatherResults = listOf(
                LocationSearchViewModel.WeatherResult("Temperature", "16C"),
                LocationSearchViewModel.WeatherResult("Apparent Temperature", "14C"),
                LocationSearchViewModel.WeatherResult("Wind", "13mph"),
            )
        )
    }
}