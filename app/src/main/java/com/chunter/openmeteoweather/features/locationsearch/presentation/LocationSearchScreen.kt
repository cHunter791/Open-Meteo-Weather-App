package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.core.ui.theme.LocalDimensions
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.components.LocationSearchField
import com.chunter.openmeteoweather.features.locationsearch.presentation.components.WeatherCard

@Composable
fun LocationSearchScreen(viewModel: LocationSearchViewModel) {
    val state by viewModel.state.collectAsState()

    LocationSearchContent(
        state = state,
        onLocationSearchAction = viewModel::handleAction,
    )
}

@Composable
private fun LocationSearchContent(
    state: LocationSearchViewModel.State,
    onLocationSearchAction: (LocationSearchViewModel.Action) -> Unit,
) {
    val weatherCardGridSpacing = LocalDimensions.current.weatherCardDimensions.gridSpacing

    Column {
        LocationSearchField(
            location = state.location,
            onSearchSubmitted = { searchString ->
                onLocationSearchAction(
                    LocationSearchViewModel.Action.SearchSubmitted(searchString)
                )
            },
        )
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = weatherCardGridSpacing,
                horizontalArrangement = Arrangement.spacedBy(weatherCardGridSpacing),
            ) {
                items(state.weatherResults) { weatherResult ->
                    WeatherCard(weatherResult = weatherResult)
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
private fun LocationSearchContentPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchContent(
            state = LocationSearchViewModel.State(
                location = "Donegall Square N, Belfast BT1 5GS",
                weatherResults = listOf(
                    LocationSearchViewModel.WeatherResult("Temperature", "16C"),
                    LocationSearchViewModel.WeatherResult("Apparent Temperature", "14C"),
                    LocationSearchViewModel.WeatherResult("Wind", "13mph"),
                )
            ),
            onLocationSearchAction = {},
        )
    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
private fun LocationSearchLoadingContentPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchContent(
            state = LocationSearchViewModel.State(isLoading = true),
            onLocationSearchAction = {},
        )
    }
}