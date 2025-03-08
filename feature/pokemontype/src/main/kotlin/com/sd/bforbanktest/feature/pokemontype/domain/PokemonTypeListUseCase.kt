package com.sd.bforbanktest.feature.pokemontype.domain

import javax.inject.Inject

class PokemonTypeListUseCase @Inject constructor(private val repository: PokemonTypeListRepository) {
    suspend fun loadTypes(): Result<List<PokemonTypeListItem>> = repository.loadTypes()
}
