package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.R
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.components.LocationSearchField
import com.chunter.openmeteoweather.features.locationsearch.presentation.components.WeatherList

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
    Column {
        LocationSearchField(
            location = state.location,
            onSearchSubmitted = { searchString ->
                onLocationSearchAction(
                    LocationSearchViewModel.Action.SearchSubmitted(searchString)
                )
            },
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when {
                state.isLoading -> CircularProgressIndicator()
                state.errorMessage != null -> Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = state.errorMessage,
                    textAlign = TextAlign.Center
                )

                else -> WeatherList(weatherResults = state.weatherResults)
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

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
private fun LocationSearchErrorContentPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchContent(
            state = LocationSearchViewModel.State(
                errorMessage = stringResource(id = R.string.message_internet_connection_error)
            ),
            onLocationSearchAction = {},
        )
    }
}