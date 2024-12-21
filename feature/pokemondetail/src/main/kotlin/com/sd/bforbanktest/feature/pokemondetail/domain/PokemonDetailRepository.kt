package com.sd.bforbanktest.feature.pokemondetail.domain

interface PokemonDetailRepository {

    suspend fun getDetail(name: String): Result<PokemonDetail>
}
