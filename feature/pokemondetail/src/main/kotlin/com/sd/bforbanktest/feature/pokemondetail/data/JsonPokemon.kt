package com.sd.bforbanktest.feature.pokemondetail.data

import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.net.URL

@JsonClass(generateAdapter = true)
data class JsonPokemon(
    val name: String,
    val sprites: JsonPokemonSprites,
    val id: Int
)

@JsonClass(generateAdapter = true)
data class JsonPokemonSprites(
    val other: JsonPokemonSpritesOther
)

@JsonClass(generateAdapter = true)
data class JsonPokemonSpritesOther(
    @Json(name = "official-artwork") val officialArtwork: JsonPokemonSpritesOtherOfficialArtwork
)

@JsonClass(generateAdapter = true)
data class JsonPokemonSpritesOtherOfficialArtwork(
    @Json(name = "front_default") val frontDefault: String
)

fun JsonPokemon.toDomain() = PokemonDetail(
    name = name,
    numberId = id,
    image = URL(sprites.other.officialArtwork.frontDefault)
)
