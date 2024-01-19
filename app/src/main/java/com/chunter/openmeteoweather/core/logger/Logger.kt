package com.chunter.openmeteoweather.core.logger

interface Logger {

    fun log(
        level: Level,
        message: String,
        exception: Exception? = null,
    )
}

enum class Level {
    Error,
    Warning,
    Info,
}