package com.sd.bforbanktest.feature.pokemondetail.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.sd.bforbank.core.common.LogService
import com.sd.bforbanktest.core.common.gateway.TestCoroutineStandard
import com.sd.bforbanktest.core.common.gateway.TestCoroutineUnconfined
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetailUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(TestCoroutineStandard::class)
class PokemonDetailViewModelTest {
    private val useCase: PokemonDetailUseCase = mockk {}
    private val logService: LogService = mockk()
    private val pokemonName = "mew"
    private val savedState = SavedStateHandle(mapOf("pokemonName" to pokemonName))

    private val viewModel by lazy {
        PokemonDetailViewModel(
            useCase = useCase,
            logService = logService,
            savedStateHandle = savedState,
        )
    }

    private val mockPokemonDetail: PokemonDetail = mockk {
        every { name } returns pokemonName
    }

    @Test
    fun `WHEN usecase loading success THEN update state success`() = runTest {
        coEvery {
            useCase.getDetail(pokemonName)
        } returns Result.success(mockPokemonDetail)

        viewModel.state.test {
            assertEquals(PokemonDetailState(name = pokemonName), awaitItem())
            assertEquals(
                PokemonDetailState(
                    name = pokemonName,
                    status = PokemonDetailState.Status.Success(mockPokemonDetail),
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `WHEN usecase loading failed THEN update state failure`() = runTest {
        val error = IllegalStateException()
        coEvery {
            useCase.getDetail(pokemonName)
        } returns Result.failure(error)

        viewModel.state.test {
            assertEquals(PokemonDetailState(name = pokemonName), awaitItem())
            assertEquals(
                PokemonDetailState(
                    name = pokemonName,
                    status = PokemonDetailState.Status.Error,
                ),
                awaitItem()
            )
        }
    }

    @Test
    @ExtendWith(TestCoroutineUnconfined::class)
    fun `WHEN ErrorRetryButtonClicked dispatched THEN reload data`() = runTest {
        coEvery {
            useCase.getDetail(pokemonName)
        } returns Result.success(mockPokemonDetail)

        viewModel.dispatch(PokemonDetailEvent.ErrorRetryButtonClicked)

        coVerify(exactly = 2) { useCase.getDetail(pokemonName) }
    }
}
