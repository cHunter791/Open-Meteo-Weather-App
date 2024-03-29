package com.chunter.openmeteoweather.features.locationsearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunter.openmeteoweather.core.di.DefaultDispatcher
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.GetWeatherForLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val getWeatherForLocationUseCase: GetWeatherForLocationUseCase,
    private val weatherStateMapper: WeatherStateMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State>
        get() = _state

    fun handleAction(action: Action) {
        when (action) {
            is Action.SearchSubmitted -> performSearch(action.searchString)
        }
    }

    private fun performSearch(location: String) {
        _state.value = state.value.copy(
            isLoading = true,
            location = location,
            errorMessage = null,
            weatherResults = emptyList(),
        )

        viewModelScope.launch(defaultDispatcher) {
            try {
                val weather = getWeatherForLocationUseCase(location)
                val weatherResults = weatherStateMapper.mapDomainToState(weather)
                _state.value = state.value.copy(
                    isLoading = false,
                    weatherResults = weatherResults,
                )
            } catch (exception: Exception) {
                _state.value = state.value.copy(
                    isLoading = false,
                    weatherResults = emptyList(),
                    errorMessage = weatherStateMapper.mapExceptionToErrorMessage(exception)
                )
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val location: String = "",
        val weatherResults: List<WeatherResult> = emptyList(),
        val errorMessage: String? = null,
    )

    data class WeatherResult(
        val title: String,
        val value: String,
    )

    sealed class Action {
        data class SearchSubmitted(val searchString: String) : Action()
    }
}
