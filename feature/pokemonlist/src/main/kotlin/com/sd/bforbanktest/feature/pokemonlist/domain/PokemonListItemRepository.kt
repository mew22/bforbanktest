package com.sd.bforbanktest.feature.pokemonlist.domain

import kotlinx.coroutines.flow.Flow

interface PokemonListItemRepository {

    val data: Flow<Set<PokemonListItem>?>
    suspend fun loadMore(limit: Int): Result<Set<PokemonListItem>>
}
