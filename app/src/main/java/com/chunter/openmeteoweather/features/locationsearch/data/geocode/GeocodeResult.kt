package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.google.gson.annotations.SerializedName

data class GeocodeResult(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
)