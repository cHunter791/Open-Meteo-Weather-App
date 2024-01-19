package com.chunter.openmeteoweather.features.locationsearch.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.chunter.openmeteoweather.core.ui.theme.LocalDimensions
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchViewModel

@Composable
fun WeatherCard(
    modifier: Modifier = Modifier,
    weatherResult: LocationSearchViewModel.WeatherResult,
) {
    val dimensions = LocalDimensions.current.weatherCardDimensions

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensions.cardPaddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensions.contentSpacing)
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = weatherResult.title,
                textAlign = TextAlign.Center
            )
            Text(
                style = MaterialTheme.typography.displaySmall,
                text = weatherResult.value,
            )
        }
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    OpenMeteoWeatherTheme {
        WeatherCard(
            weatherResult = LocationSearchViewModel.WeatherResult(
                title = "Temperature",
                value = "16C",
            ),
        )
    }
}
