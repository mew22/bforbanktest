package com.sd.bforbanktest.feature.pokemontype.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonResponse(
    val results: List<JsonTypeListItem>,
)
