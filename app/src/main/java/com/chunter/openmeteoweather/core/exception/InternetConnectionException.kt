package com.chunter.openmeteoweather.core.exception

class InternetConnectionException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)