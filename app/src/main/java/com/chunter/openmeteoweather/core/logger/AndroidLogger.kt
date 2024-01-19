package com.chunter.openmeteoweather.core.logger

import android.util.Log
import javax.inject.Inject

class AndroidLogger @Inject constructor() : Logger {

    override fun log(
        level: Level,
        message: String,
        exception: Exception?,
    ) {
        when (level) {
            Level.Error -> Log.e(null, message, exception)
            Level.Warning -> Log.w(null, message, exception)
            Level.Info -> Log.i(null, message, exception)
        }
    }
}