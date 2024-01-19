package com.chunter.openmeteoweather.features.locationsearch.domain.geocode

class NoGeocodeResultFoundException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)