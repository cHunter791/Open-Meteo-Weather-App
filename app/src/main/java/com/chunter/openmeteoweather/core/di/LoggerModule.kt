package com.chunter.openmeteoweather.core.di

import com.chunter.openmeteoweather.core.logger.AndroidLogger
import com.chunter.openmeteoweather.core.logger.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    abstract fun bindLogger(logger: AndroidLogger): Logger
}