package com.sd.bforbanktest.feature.pokemontype.data

import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListItem
import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListRepository
import javax.inject.Inject

internal class PokemonTypeListRepositoryImpl @Inject constructor(
    private val service: PokemonTypeListService,
) : PokemonTypeListRepository {
    override suspend fun loadTypes(): Result<List<PokemonTypeListItem>> =
        service.loadTypes().mapCatching {
            it.results.toDomain()
        }
}
