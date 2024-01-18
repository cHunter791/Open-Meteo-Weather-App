package com.chunter.openmeteoweather.core.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimensions = compositionLocalOf { Dimensions() }

data class Dimensions(
    val searchFieldDimensions: SearchFieldDimensions = SearchFieldDimensions(),
    val weatherCardDimensions: WeatherCardDimensions = WeatherCardDimensions(),
)

data class SearchFieldDimensions(
    val height: Dp = 64.dp
)

data class WeatherCardDimensions(
    val cardPaddingValues: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
    val contentSpacing: Dp = 32.dp,
    val gridSpacing: Dp = 8.dp,
)