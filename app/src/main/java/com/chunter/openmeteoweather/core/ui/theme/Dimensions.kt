package com.chunter.openmeteoweather.core.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimensions = compositionLocalOf { Dimensions() }

data class Dimensions(
    val searchFieldDimensions: SearchFieldDimensions = SearchFieldDimensions(),
)

data class SearchFieldDimensions(
    val height: Dp = 64.dp
)