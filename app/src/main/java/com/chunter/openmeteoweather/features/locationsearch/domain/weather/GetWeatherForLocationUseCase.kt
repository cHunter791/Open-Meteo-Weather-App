package com.chunter.openmeteoweather.features.locationsearch.domain.weather

import com.chunter.openmeteoweather.core.logger.Level
import com.chunter.openmeteoweather.core.logger.Logger
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.GeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFoundException
import javax.inject.Inject

class GetWeatherForLocationUseCase @Inject constructor(
    private val logger: Logger,
    private val geocodeRepository: GeocodeRepository,
    private val weatherRepository: WeatherRepository,
) {

    suspend operator fun invoke(location: String): Weather {
        return try {
            val latLng = geocodeRepository.forwardGeocode(location)
            weatherRepository.getWeatherData(latLng)
        } catch (noGeocodeResultFoundException: NoGeocodeResultFoundException) {
            logError(
                exception = noGeocodeResultFoundException,
                fallbackMessage = "Error retrieving geocode data",
            )
            throw noGeocodeResultFoundException
        } catch (exception: Exception) {
            logError(
                exception = exception,
                fallbackMessage = "Unknown error occurred retrieving weather data",
            )
            throw exception
        }
    }

    private fun logError(exception: Exception, fallbackMessage: String) {
        logger.log(
            level = Level.Error,
            message = exception.message ?: fallbackMessage,
            exception = exception,
        )
    }
}