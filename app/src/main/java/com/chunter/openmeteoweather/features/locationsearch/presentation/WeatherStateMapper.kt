package com.chunter.openmeteoweather.features.locationsearch.presentation

import android.content.res.Resources
import androidx.annotation.StringRes
import com.chunter.openmeteoweather.R
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFoundException
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.Weather
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherValue
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherStateMapper @Inject constructor(
    private val resources: Resources
) {

    /**
     * Takes the domain weather data and maps it to the state class for the location search screen.
     * We attempt to map each property to the [LocationSearchViewModel.WeatherResult] class but if
     * the domain data is `null` it will be omitted.
     */
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

    fun mapExceptionToErrorMessage(exception: Exception): String {
        return when (exception) {
            is NoGeocodeResultFoundException -> resources.getString(R.string.message_no_location)
            is SocketTimeoutException, is UnknownHostException -> resources.getString(R.string.message_internet_connection_error)
            else -> resources.getString(R.string.message_unknown_error)
        }
    }

    private fun WeatherValue?.toWeatherResult(@StringRes titleResId: Int): LocationSearchViewModel.WeatherResult? {
        if (this == null) return null
        return LocationSearchViewModel.WeatherResult(
            title = resources.getString(titleResId),
            value = "${value}${unit}",
        )
    }
}