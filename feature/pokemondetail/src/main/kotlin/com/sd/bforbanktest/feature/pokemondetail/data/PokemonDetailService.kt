package com.sd.bforbanktest.feature.pokemondetail.data

import retrofit2.http.GET
import retrofit2.http.Path

internal interface PokemonDetailService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String,
        ): Result<JsonPokemon>
}
