package com.sd.bforbanktest.feature.pokemondetail.data.mock

import com.sd.bforbanktest.feature.pokemondetail.data.JsonPokemon
import com.sd.bforbanktest.feature.pokemondetail.data.JsonPokemonSprites
import com.sd.bforbanktest.feature.pokemondetail.data.JsonPokemonSpritesOther
import com.sd.bforbanktest.feature.pokemondetail.data.JsonPokemonSpritesOtherOfficialArtwork
import com.sd.bforbanktest.feature.pokemondetail.data.PokemonDetailService
import kotlinx.coroutines.delay

object MockService {

    private val pokemon = JsonPokemon(
        name = "mew",
        id = 151,
        sprites = JsonPokemonSprites(
            other = JsonPokemonSpritesOther(
                officialArtwork = JsonPokemonSpritesOtherOfficialArtwork(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/" + "sprites/pokemon/other/official-artwork/151.png"
                )
            )
        )
    )

    internal fun create() = object : PokemonDetailService {
        override suspend fun getPokemon(name: String): Result<JsonPokemon> {
            delay(timeMillis = 2000)
            return Result.success(pokemon)
        }
    }
}
