package com.chunter.openmeteoweather.features.locationsearch.domain.geocode

class NoGeocodeResultFound(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)