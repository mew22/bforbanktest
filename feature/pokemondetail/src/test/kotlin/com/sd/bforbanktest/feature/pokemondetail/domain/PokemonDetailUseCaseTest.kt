package com.sd.bforbanktest.feature.pokemondetail.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PokemonDetailUseCaseTest {

    private val pokemonName = "mew"
    private val mockPokemon: PokemonDetail = mockk()

    private val repository: PokemonDetailRepository = mockk {}

    private val useCase = PokemonDetailUseCase(
        repository = repository,
    )

    @Test
    fun `WHEN getDetail is called THEN load from repository success`() = runTest {
        coEvery { repository.getDetail(pokemonName) } returns Result.success(mockPokemon)

        val result = useCase.getDetail(pokemonName)

        assertEquals(result.getOrNull(), mockPokemon)
    }

    @Test
    fun `WHEN loadMore is called THEN load from repository failure`() = runTest {
        val failure = IllegalArgumentException()
        coEvery { repository.getDetail(pokemonName) } returns Result.failure(failure)

        val result = useCase.getDetail(pokemonName)

        assertEquals(result.exceptionOrNull(), failure)
    }
}
