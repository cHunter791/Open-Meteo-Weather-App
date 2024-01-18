package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationSearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(State("Donegall Square N, Belfast BT1 5GS"))
    val state: StateFlow<State>
        get() = _state

    data class State(
        val location: String? = null
    )
}
