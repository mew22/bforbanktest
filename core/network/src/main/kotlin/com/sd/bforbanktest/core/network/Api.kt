package com.sd.bforbanktest.core.network

import javax.inject.Inject

class Api @Inject constructor() {
    operator fun invoke(): String = API_ENDPOINT
    companion object {
        private const val API_ENDPOINT = "https://pokeapi.co/api/v2/"
    }
}
