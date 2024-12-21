package com.sd.bforbanktest.feature.pokemondetail.domain

import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val repository: PokemonDetailRepository,
) {

    suspend fun getDetail(name: String): Result<PokemonDetail> = repository.getDetail(name)
}
