package com.chunter.openmeteoweather.features.locationsearch.presentation

import android.content.res.Resources
import androidx.annotation.StringRes
import com.chunter.openmeteoweather.R
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherValue
import javax.inject.Inject

class WeatherStateMapper @Inject constructor(
    private val resources: Resources
) {

    fun mapDomainToState(weather: Weather): List<LocationSearchViewModel.WeatherResult> {
        return listOfNotNull(
            weather.temperature.toWeatherResult(R.string.title_temperature),
            weather.apparentTemperature.toWeatherResult(R.string.title_apparent_temperature),
            weather.rain.toWeatherResult(R.string.title_rain),
            weather.cloudCover.toWeatherResult(R.string.title_cloud_cover),
            weather.windSpeed.toWeatherResult(R.string.title_wind_speed),
            weather.windDirection.toWeatherResult(R.string.title_wind_direction),
        )
    }

    private fun WeatherValue?.toWeatherResult(@StringRes titleResId: Int): LocationSearchViewModel.WeatherResult? {
        if (this == null) return null
        return LocationSearchViewModel.WeatherResult(
            title = resources.getString(titleResId),
            value = "${value}${unit}",
        )
    }
}