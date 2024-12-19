package com.sd.bforbank.core.common

import kotlinx.coroutines.CancellationException

@Suppress("TooGenericExceptionCaught")
inline fun <T, R> T.runCatchingSafe(block: T.() -> R): Result<R> = try {
    Result.success(block())
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    Result.failure(e)
}
