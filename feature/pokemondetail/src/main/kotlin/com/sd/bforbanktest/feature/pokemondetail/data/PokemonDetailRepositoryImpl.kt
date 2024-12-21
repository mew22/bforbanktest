package com.sd.bforbanktest.feature.pokemondetail.data

import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetailRepository
import javax.inject.Inject

internal class PokemonDetailRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonDetailService,
) : PokemonDetailRepository {

    override suspend fun getDetail(name: String): Result<PokemonDetail> =
        pokemonService.getPokemon(name).mapCatching { it.toDomain() }
}
