package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme

@Composable
fun LocationSearchScreen(viewModel: LocationSearchViewModel) {
    val state by viewModel.state.collectAsState()

    LocationSearchContent(state = state)
}

@Composable
private fun LocationSearchContent(state: LocationSearchViewModel.State) {
    Column {
        state.location?.let { location -> Text(text = location) }
    }
}

@Preview
@Composable
private fun LocationSearchContentPreview() {
    OpenMeteoWeatherTheme {
        LocationSearchContent(state = LocationSearchViewModel.State("Donegall Square N, Belfast BT1 5GS"))
    }
}