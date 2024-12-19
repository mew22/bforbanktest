package com.sd.bforbank.core.common

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
