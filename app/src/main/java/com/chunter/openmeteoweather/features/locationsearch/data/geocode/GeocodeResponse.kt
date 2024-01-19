package com.chunter.openmeteoweather.features.locationsearch.data.geocode

import com.google.gson.annotations.SerializedName

data class GeocodeResponse(
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lon")
    val longitude: Double?,
)