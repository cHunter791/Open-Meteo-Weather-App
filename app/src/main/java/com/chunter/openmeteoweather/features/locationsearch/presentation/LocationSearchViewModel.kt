package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationSearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        State(
            location = "Donegall Square N, Belfast BT1 5GS",
            weatherResults = listOf(
                WeatherResult("Temperature", "16C"),
                WeatherResult("Apparent Temperature", "14C"),
                WeatherResult("Wind", "13mph", "East"),
            )
        )
    )
    val state: StateFlow<State>
        get() = _state

    fun handleAction(action: Action) {

    }

    data class State(
        val location: String = "",
        val weatherResults: List<WeatherResult> = emptyList(),
    )

    data class WeatherResult(
        val title: String,
        val value: String,
        val extraValue: String? = null,
    )

    sealed class Action {
        data class SearchSubmitted(val searchString: String) : Action()
    }
}
