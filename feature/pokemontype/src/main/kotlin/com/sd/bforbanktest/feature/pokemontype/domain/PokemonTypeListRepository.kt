package com.sd.bforbanktest.feature.pokemontype.domain

interface PokemonTypeListRepository {
    suspend fun loadTypes(): Result<List<PokemonTypeListItem>>
}
