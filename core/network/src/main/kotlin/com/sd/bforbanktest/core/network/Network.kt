package com.sd.bforbanktest.core.network

import retrofit2.Retrofit
import retrofit2.create

class Network internal constructor(
    val retrofit: Retrofit?,
) {

    inline fun <reified T> create(mockFactory: () -> T): T =
        retrofit?.create() ?: mockFactory()
}
