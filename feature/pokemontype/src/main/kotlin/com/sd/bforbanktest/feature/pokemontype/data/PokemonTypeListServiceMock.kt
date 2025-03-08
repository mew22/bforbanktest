package com.sd.bforbanktest.feature.pokemontype.data

import kotlinx.coroutines.delay

object PokemonTypeListServiceMock {
    private val list = listOf(
        JsonTypeListItem("normal"),
        JsonTypeListItem("fire"),
        JsonTypeListItem("water"),
        JsonTypeListItem("electric"),
        JsonTypeListItem("grass"),
        JsonTypeListItem("ice"),
        JsonTypeListItem("fighting"),
        JsonTypeListItem("poison"),
        JsonTypeListItem("ground"),
        JsonTypeListItem("flying"),
        JsonTypeListItem("psychic"),
        JsonTypeListItem("bug"),
        JsonTypeListItem("rock"),
        JsonTypeListItem("ghost"),
        JsonTypeListItem("dragon"),
        JsonTypeListItem("dark"),
        JsonTypeListItem("steel"),
        JsonTypeListItem("fairy"),
        JsonTypeListItem("unknown"),
    )

    internal fun create() = object : PokemonTypeListService {
        override suspend fun loadTypes(): Result<JsonResponse> {
            delay(timeMillis = 3000)
            return Result.success(
                JsonResponse(list)
            )
        }
    }
}
