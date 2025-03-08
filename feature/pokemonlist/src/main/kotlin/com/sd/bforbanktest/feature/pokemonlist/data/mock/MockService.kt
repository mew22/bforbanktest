package com.sd.bforbanktest.feature.pokemonlist.data.mock

import com.sd.bforbanktest.feature.pokemonlist.data.JsonPokemonListItem
import com.sd.bforbanktest.feature.pokemonlist.data.JsonResponse
import com.sd.bforbanktest.feature.pokemonlist.data.JsonTypeResponse
import com.sd.bforbanktest.feature.pokemonlist.data.JsonTypeResponsePokemon
import com.sd.bforbanktest.feature.pokemonlist.data.PokemonListService
import kotlinx.coroutines.delay

object MockService {

    private val pokemonList = listOf(
        JsonPokemonListItem(name = "bulbasaur"),
        JsonPokemonListItem(name = "ivysaur"),
        JsonPokemonListItem(name = "venusaur"),
        JsonPokemonListItem(name = "charmander"),
        JsonPokemonListItem(name = "charmeleon"),
        JsonPokemonListItem(name = "charizard"),
        JsonPokemonListItem(name = "squirtle"),
        JsonPokemonListItem(name = "wartortle"),
        JsonPokemonListItem(name = "blastoise"),
        JsonPokemonListItem(name = "caterpie"),
        JsonPokemonListItem(name = "metapod"),
        JsonPokemonListItem(name = "butterfree"),
        JsonPokemonListItem(name = "weedle"),
        JsonPokemonListItem(name = "kakuna"),
        JsonPokemonListItem(name = "beedrill"),
        JsonPokemonListItem(name = "pidgey"),
        JsonPokemonListItem(name = "pidgeotto"),
        JsonPokemonListItem(name = "pidgeot"),
        JsonPokemonListItem(name = "rattata"),
        JsonPokemonListItem(name = "raticate"),
        JsonPokemonListItem(name = "spearow"),

        JsonPokemonListItem(name = "2bulbasaur"),
        JsonPokemonListItem(name = "2ivysaur"),
        JsonPokemonListItem(name = "2venusaur"),
        JsonPokemonListItem(name = "2charmander"),
        JsonPokemonListItem(name = "2charmeleon"),
        JsonPokemonListItem(name = "2charizard"),
        JsonPokemonListItem(name = "2squirtle"),
        JsonPokemonListItem(name = "2wartortle"),
        JsonPokemonListItem(name = "2blastoise"),
        JsonPokemonListItem(name = "2caterpie"),
        JsonPokemonListItem(name = "2metapod"),
        JsonPokemonListItem(name = "2butterfree"),
        JsonPokemonListItem(name = "2weedle"),
        JsonPokemonListItem(name = "2kakuna"),
        JsonPokemonListItem(name = "2beedrill"),
        JsonPokemonListItem(name = "2pidgey"),
        JsonPokemonListItem(name = "2pidgeotto"),
        JsonPokemonListItem(name = "2pidgeot"),
        JsonPokemonListItem(name = "2rattata"),
        JsonPokemonListItem(name = "2raticate"),
        JsonPokemonListItem(name = "2spearow"),
    )

    internal fun create() = object : PokemonListService {

        override suspend fun getPagedPokemon(
            limit: Int,
            offset: Int
        ): Result<JsonResponse> {
            delay(timeMillis = 5000)
            return Result.success(
                JsonResponse(
                    pokemonList.drop(offset).take(limit)
                )
            )
        }

        override suspend fun getPagedPokemonByType(
            typeName: String,
            limit: Int,
            offset: Int
        ): Result<JsonTypeResponse> {
            delay(timeMillis = 5000)
            return Result.success(
                JsonTypeResponse(
                    pokemonList.map {
                        JsonTypeResponsePokemon(it)
                    }
                        .drop(offset)
                        .take(limit)
                )
            )
        }
    }
}
