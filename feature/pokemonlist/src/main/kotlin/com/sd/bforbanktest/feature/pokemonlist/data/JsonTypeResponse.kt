package com.sd.bforbanktest.feature.pokemonlist.data

import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import com.squareup.moshi.JsonClass
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet

@JsonClass(generateAdapter = true)
data class JsonTypeResponse(
    val pokemon: List<JsonTypeResponsePokemon>,
)

@JsonClass(generateAdapter = true)
data class JsonTypeResponsePokemon(
    val pokemon: JsonPokemonListItem
)

fun JsonTypeResponsePokemon.toDomain() = PokemonListItem(
    name = pokemon.name,
)

fun List<JsonTypeResponsePokemon>.toDomain(): PersistentSet<PokemonListItem> =
    map { it.toDomain() }.toPersistentSet()
