package com.sd.bforbanktest.feature.pokemonlist.data

import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItemRepository
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class PokemonListItemRepositoryImpl @Inject constructor(
    private val pokemonListService: PokemonListService,
) : PokemonListItemRepository {
    private var page = 0
    override val data = MutableStateFlow<PersistentSet<PokemonListItem>?>(null)

    override suspend fun loadMore(limit: Int): Result<PersistentSet<PokemonListItem>> =
        pokemonListService.getPagedPokemon(limit, page * limit)
            .mapCatching { it.results.toDomain() }
            .onSuccess { newList ->
                page++
                data.update { state ->
                    state?.addAll(newList) ?: newList.toPersistentSet()
                }
            }
}
