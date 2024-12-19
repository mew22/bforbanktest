package com.sd.bforbanktest.feature.pokemonlist.domain

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PokemonListItemUseCaseTest {

    private val mockDataSet = setOf(
        PokemonListItem("a"),
        PokemonListItem("b"),
        PokemonListItem("c"),
    )

    private val dataFlow = MutableSharedFlow<Set<PokemonListItem>>()
    private val repository: PokemonListItemRepository = mockk {
        every { data } returns dataFlow
    }

    private val useCase = PokemonListItemUseCase(
        repository = repository,
    )

    @Test
    fun `WHEN loadMore is called THEN load from repository success`() = runTest {
        coEvery { repository.loadMore(ITEM_PER_PAGE) } returns Result.success(mockDataSet)

        val result = useCase.loadMore(ITEM_PER_PAGE)

        assertTrue(result.isSuccess)
    }

    @Test
    fun `WHEN loadMore is called THEN load from repository failure`() = runTest {
        val failure = IllegalArgumentException()
        coEvery {
            repository.loadMore(ITEM_PER_PAGE)
        } returns Result.failure(failure)

        val result = useCase.loadMore(ITEM_PER_PAGE)

        assertEquals(result.exceptionOrNull(), failure)
    }

    @Test
    fun `WHEN repository provide data THEN provide those data`() = runTest {
        useCase.data.test {
            dataFlow.emit(mockDataSet)
            assertEquals(mockDataSet, awaitItem())
        }
    }

    companion object {
        const val ITEM_PER_PAGE = 30
    }
}
