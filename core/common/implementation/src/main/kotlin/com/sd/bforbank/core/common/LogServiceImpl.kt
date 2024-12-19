package com.sd.bforbank.core.common

import android.util.Log
import javax.inject.Inject

class LogServiceImpl @Inject constructor() : LogService {
    override fun logNonFatalCrash(throwable: Throwable) {
        Log.e("LOGGER", throwable.message, throwable)
    }
}
