package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.chunter.openmeteoweather.BuildConfig
import javax.inject.Inject

/**
 * A wrapper class over the generated [BuildConfig] class. This is to aid us in
 * writing unit tests as we can mock this class instead of trying to rely on/mock
 * the static [BuildConfig] class
 */
class GeocodeApiKeyProvider @Inject constructor() {

    val key: String
        get() = BuildConfig.geocodeApiKey
}