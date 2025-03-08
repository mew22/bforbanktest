package com.sd.bforbanktest.feature.pokemonlist.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonListService {

    @GET("pokemon")
    suspend fun getPagedPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        ): Result<JsonResponse>

    @GET("type/{typeName}")
    suspend fun getPagedPokemonByType(
        @Path("typeName") typeName: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Result<JsonTypeResponse>
}
