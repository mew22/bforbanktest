package com.sd.bforbanktest.feature.pokemonlist.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonResponse(
    val results: List<JsonPokemonListItem>,
)
