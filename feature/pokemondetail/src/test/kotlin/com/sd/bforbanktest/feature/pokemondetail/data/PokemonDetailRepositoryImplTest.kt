package com.sd.bforbanktest.feature.pokemondetail.data

import com.sd.bforbanktest.core.network.NetworkException
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PokemonDetailRepositoryImplTest {

    private val pokemonName = "mew"
    private val mockPokemon: PokemonDetail = mockk()
    private val mockJsonPokemon: JsonPokemon = mockk()

    private val service: PokemonDetailService = mockk()

    private val repository = PokemonDetailRepositoryImpl(service)

    @Test
    fun `WHEN getDetail is called THEN load from service success`() = runTest {
        coEvery {
            service.getPokemon(pokemonName)
        } returns Result.success(mockJsonPokemon)

        mockkStatic(JsonPokemon::toDomain) {
            every { mockJsonPokemon.toDomain() } returns mockPokemon
            val result = repository.getDetail(pokemonName)
            assertEquals(result.getOrThrow(), mockPokemon)
        }
    }

    @Test
    fun `WHEN getDetail is called THEN load from service failure`() = runTest {
        val failure = NetworkException(httpCode = 404)
        coEvery {
            service.getPokemon(pokemonName)
        } returns Result.failure(failure)

        val result = repository.getDetail(pokemonName)

        assertEquals(result.exceptionOrNull(), failure)
    }
}
