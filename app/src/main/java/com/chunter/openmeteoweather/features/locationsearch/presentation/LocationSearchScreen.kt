package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.components.LocationSearchField

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
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun LocationSearchContentPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchContent(
            state = LocationSearchViewModel.State("Donegall Square N, Belfast BT1 5GS"),
            onLocationSearchAction = {},
        )
    }
}