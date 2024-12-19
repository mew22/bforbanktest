package com.sd.bforbanktest.feature.pokemonlist.ui

import app.cash.turbine.test
import com.sd.bforbank.core.common.LogService
import com.sd.bforbanktest.core.common.gateway.TestCoroutineStandard
import com.sd.bforbanktest.core.common.gateway.TestCoroutineUnconfined
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItemUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(TestCoroutineStandard::class)
class PokemonListViewModelTest {
    private val dataFlow = MutableSharedFlow<Set<PokemonListItem>>()
    private val useCase: PokemonListItemUseCase = mockk {
        every { data } returns dataFlow
    }
    private val logService: LogService = mockk()

    private val viewModel by lazy {
        PokemonListViewModel(
            useCase = useCase,
            logService = logService,
        )
    }

    private val mockDataSet = setOf(
        PokemonListItem("a"),
        PokemonListItem("b"),
        PokemonListItem("c"),
    )

    private val mockDataImmutableList = mockDataSet.toPersistentList()

    @Test
    fun `GIVEN usecase has data WHEN usecase provide data THEN update state`() = runTest {
        coEvery {
            useCase.loadMore(ITEM_PER_PAGE)
        } returns Result.success(Unit)

        viewModel.state.test {
            assertEquals(PokemonListState(isLoading = true), awaitItem())
            assertEquals(PokemonListState(isLoading = false), awaitItem())
        }

        dataFlow.emit(mockDataSet)

        viewModel.state.test {
            assertEquals(
                PokemonListState(
                    status = PokemonListState.Status.Success(mockDataImmutableList)
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `WHEN usecase loading failed THEN update state`() = runTest {
        coEvery {
            useCase.loadMore(ITEM_PER_PAGE)
        } returns Result.failure(IllegalArgumentException())

        viewModel.state.test {
            assertEquals(PokemonListState(isLoading = true), awaitItem())
            assertEquals(
                PokemonListState(isLoading = false, status = PokemonListState.Status.Error),
                awaitItem(),
            )
        }
    }

    @Test
    @ExtendWith(TestCoroutineUnconfined::class)
    fun `WHEN ListReachBottom event is received THEN call usecase loadMore with next page`() =
        runTest {
            coEvery {
                useCase.loadMore(ITEM_PER_PAGE)
            } returns Result.success(Unit)

            viewModel.dispatch(PokemonListEvent.ListReachBottom)

            coVerify(exactly = 2) { useCase.loadMore(ITEM_PER_PAGE) }
        }

    companion object {
        private const val ITEM_PER_PAGE = 20
    }
}
