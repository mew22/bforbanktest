package com.sd.bforbank.core.common

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException
import kotlin.time.Duration

private const val LIFECYCLE_TIMEOUT = 5_000L

fun <T> Flow<T>.stateIn(scope: CoroutineScope, initialState: T): StateFlow<T> =
    stateIn(scope, SharingStarted.WhileSubscribed(LIFECYCLE_TIMEOUT), initialState)

suspend fun <T> Flow<T>.toFirstResult(): Result<T> = runCatchingSafe {
    this.map { it }.first()
}

fun <T> Flow<T>.repeatIf(count: Int, delayMs: Long, predicate: (T) -> Boolean): Flow<T> {
    require(count >= 1) { "`count` must be larger or equal to 1" }
    require(delayMs >= 0) { "`delayMs` must be a positive number" }
    return flow {
        var remainingAttempts = count
        do {
            val returnValue = last()
            remainingAttempts--
            if (remainingAttempts == 0 || !predicate(returnValue)) {
                emit(returnValue)
                return@flow
            }
            delay(delayMs)
        } while (remainingAttempts != 0)
    }
}

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }
    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastTime >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}

fun <T> Flow<T>.withTimeout(duration: Duration?): Flow<T> = if (duration == null) {
    this
} else {
    channelFlow {
        val collector = launch {
            collect {
                send(it)
            }
            close()
        }
        delay(duration)
        if (collector.isActive) {
            collector.cancel(cause = CancellationException())
            close(TimeoutException())
        }
    }
}

suspend fun <T> Flow<T>.safeFirst(): Result<T> = runCatchingSafe {
    firstOrNull() ?: error("Flow didn't emit any value")
}

suspend inline fun <T, R> Flow<T>.safeFold(
    initial: R,
    crossinline operation: suspend (acc: R, value: T) -> R,
): Result<R> = runCatchingSafe {
    fold(initial) { acc, value -> operation(acc, value) }
}
