package com.sd.bforbanktest.feature.pokemonlist.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class PokemonListItemUseCase @Inject constructor(
    private val repository: PokemonListItemRepository,
) {
    val data: Flow<Set<PokemonListItem>> = repository.data.mapNotNull { it }

    suspend fun loadMore(limit: Int): Result<Unit> =
        repository.loadMore(limit = limit).map { }
}
