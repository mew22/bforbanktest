package com.sd.bforbanktest.core.network

data class NetworkException(
    val httpCode: Int,
) : RuntimeException()
