package com.chunter.openmeteoweather.features.locationsearch.data.di

import com.chunter.openmeteoweather.features.locationsearch.data.geocode.GeocodeApi
import com.chunter.openmeteoweather.features.locationsearch.data.weather.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ApiModule {

    private val commonRetrofitBuilder: Retrofit.Builder
        get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideGeocodeApi(): GeocodeApi {
        return commonRetrofitBuilder
            .baseUrl("https://geocode.maps.co/")
            .build()
            .create(GeocodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return commonRetrofitBuilder
            .baseUrl("https://api.open-meteo.com/v1/")
            .build().create(WeatherApi::class.java)
    }
}