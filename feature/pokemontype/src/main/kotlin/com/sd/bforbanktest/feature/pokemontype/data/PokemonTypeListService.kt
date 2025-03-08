package com.sd.bforbanktest.feature.pokemontype.data

import retrofit2.http.GET

internal interface PokemonTypeListService {
    @GET("type")
    suspend fun loadTypes(): Result<JsonResponse>
}
