package com.sd.bforbanktest.feature.pokemonlist.data

import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import com.squareup.moshi.JsonClass
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet

@JsonClass(generateAdapter = true)
data class JsonPokemonListItem(
    val name: String,
)

fun JsonPokemonListItem.toDomain() = PokemonListItem(
    name = name,
)

fun List<JsonPokemonListItem>.toDomain(): PersistentSet<PokemonListItem> =
    map { it.toDomain() }.toPersistentSet()
