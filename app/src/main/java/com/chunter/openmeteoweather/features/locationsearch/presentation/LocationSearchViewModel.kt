package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationSearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state

    fun handleAction(action: Action) {
        when (action) {
            is Action.SearchSubmitted -> performSearch(action.searchString)
        }
    }

    private fun performSearch(location: String) {
        _state.value = State(
            location = location,
            weatherResults = listOf(
                WeatherResult("Temperature", "16C"),
                WeatherResult("Apparent Temperature", "14C"),
                WeatherResult("Wind", "13mph", "East"),
            ),
        )
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
