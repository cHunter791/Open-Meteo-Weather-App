package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.chunter.openmeteoweather.BuildConfig
import javax.inject.Inject

class GeocodeApiKeyProvider @Inject constructor() {

    val key: String
        get() = BuildConfig.geocodeApiKey
}