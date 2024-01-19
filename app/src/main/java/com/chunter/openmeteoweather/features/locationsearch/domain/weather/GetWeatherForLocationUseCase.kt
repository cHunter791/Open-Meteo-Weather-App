package com.chunter.openmeteoweather.features.locationsearch.domain.weather

import com.chunter.openmeteoweather.core.logger.Level
import com.chunter.openmeteoweather.core.logger.Logger
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.GeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFoundException

class GetWeatherForLocationUseCase(
    private val logger: Logger,
    private val geocodeRepository: GeocodeRepository,
    private val weatherRepository: WeatherRepository,
) {

    suspend operator fun invoke(location: String): Weather {
        return try {
            val latLng = geocodeRepository.forwardGeocode(location)
            weatherRepository.getWeatherData(latLng)
        } catch (noGeocodeResultFoundException: NoGeocodeResultFoundException) {
            logger.log(
                level = Level.Error,
                message = noGeocodeResultFoundException.message ?: "Error retrieving geocode data",
                exception = noGeocodeResultFoundException,
            )
            throw noGeocodeResultFoundException
        } catch (exception: Exception) {
            logger.log(
                level = Level.Error,
                message = exception.message ?: "Unknown error occurred retrieving weather data",
                exception = exception,
            )
            throw exception
        }
    }
}