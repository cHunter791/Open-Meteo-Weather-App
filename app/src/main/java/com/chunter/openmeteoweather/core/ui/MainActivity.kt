package com.chunter.openmeteoweather.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.chunter.openmeteoweather.core.ui.theme.OpenMeteoWeatherTheme
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchScreen
import com.chunter.openmeteoweather.features.locationsearch.presentation.LocationSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val locationSearchViewModel: LocationSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenMeteoWeatherTheme {
                LocationSearchScreen(viewModel = locationSearchViewModel)
            }
        }
    }
}
