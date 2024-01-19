package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.GeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.LatLng
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.NoGeocodeResultFoundException
import javax.inject.Inject

class RemoteGeocodeRepository @Inject constructor(
    private val geocodeApi: GeocodeApi,
    private val geocodeApiKeyProvider: GeocodeApiKeyProvider,
) : GeocodeRepository {

    override suspend fun forwardGeocode(location: String): LatLng {
        return try {
            geocodeApi.forwardGeocode(
                location = location,
                apiKey = geocodeApiKeyProvider.key,
            ).mapNotNull { geocodeResult ->
                LatLng(
                    latitude = geocodeResult.latitude ?: return@mapNotNull null,
                    longitude = geocodeResult.longitude ?: return@mapNotNull null,
                )
            }.first()
        } catch (noSuchElementException: NoSuchElementException) {
            throw NoGeocodeResultFoundException(cause = noSuchElementException)
        }
    }
}