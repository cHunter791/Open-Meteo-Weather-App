package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.chunter.openmeteoweather.BuildConfig

class GeocodeApiKeyProvider {

    val key: String
        get() = BuildConfig.geocodeApiKey
}