package com.chunter.openmeteoweather.features.locationsearch.domain.geocode

interface GeocodeRepository {

    suspend fun forwardGeocode(location: String): LatLng
}