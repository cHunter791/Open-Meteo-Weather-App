package com.chunter.openmeteoweather.features.locationsearch.data.di

import com.chunter.openmeteoweather.features.locationsearch.data.geocode.RemoteGeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.data.weather.RemoteWeatherRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.geocode.GeocodeRepository
import com.chunter.openmeteoweather.features.locationsearch.domain.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGeocodeRepository(repository: RemoteGeocodeRepository): GeocodeRepository

    @Binds
    abstract fun bindWeatherRepository(repository: RemoteWeatherRepository): WeatherRepository
}