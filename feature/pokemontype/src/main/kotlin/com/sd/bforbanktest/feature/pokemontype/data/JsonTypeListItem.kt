package com.sd.bforbanktest.feature.pokemontype.data

import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonTypeListItem(
    val name: String,
)

fun JsonTypeListItem.toDomain(): PokemonTypeListItem = PokemonTypeListItem(name)
fun List<JsonTypeListItem>.toDomain(): List<PokemonTypeListItem> = this.map { it.toDomain() }
