package com.sd.bforbanktest.feature.pokemonlist.data

import app.cash.turbine.test
import com.sd.bforbanktest.core.network.NetworkException
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PokemonListItemRepositoryImplTest {

    private val mockPokemon1: PokemonListItem = mockk()
    private val mockJsonPokemon1: JsonPokemonListItem = mockk()

    private val mockPokemon2: PokemonListItem = mockk()
    private val mockJsonPokemon2: JsonPokemonListItem = mockk()
    private val mockPokemon3: PokemonListItem = mockk()
    private val mockJsonPokemon3: JsonPokemonListItem = mockk()
    private val mockPokemon4: PokemonListItem = mockk()
    private val mockJsonPokemon4: JsonPokemonListItem = mockk()
    private val mockPokemon5: PokemonListItem = mockk()
    private val mockJsonPokemon5: JsonPokemonListItem = mockk()

    private val mockJsonDataSet1: List<JsonPokemonListItem> = listOf(
        mockJsonPokemon1,
        mockJsonPokemon2,
        mockJsonPokemon3,
    )
    private val mockJsonResponse1: JsonResponse = mockk {
        every { results } returns mockJsonDataSet1
    }
    private val mockDataSet1: PersistentSet<PokemonListItem> = persistentSetOf(
        mockPokemon1,
        mockPokemon2,
        mockPokemon3,
    )

    private val mockJsonDataSet2: List<JsonPokemonListItem> = listOf(
        mockJsonPokemon3,
        mockJsonPokemon4,
        mockJsonPokemon5,
    )
    private val mockJsonResponse2: JsonResponse = mockk {
        every { results } returns mockJsonDataSet2
    }
    private val mockDataSet2: PersistentSet<PokemonListItem> = persistentSetOf(
        mockPokemon3,
        mockPokemon4,
        mockPokemon5,
    )

    private val mockDataSetUnion: PersistentSet<PokemonListItem> = persistentSetOf(
        mockPokemon1,
        mockPokemon2,
        mockPokemon3,
        mockPokemon4,
        mockPokemon5,
    )

    private val service: PokemonListService = mockk()

    private val repository = PokemonListItemRepositoryImpl(service)

    private var page = 0
    private val offset
        get() = page * ITEM_PER_PAGE

    @BeforeEach
    fun setUp() {
        mockkStatic(JsonPokemonListItem::toDomain)
        every { mockJsonPokemon1.toDomain() } returns mockPokemon1
        every { mockJsonPokemon2.toDomain() } returns mockPokemon2
        every { mockJsonPokemon3.toDomain() } returns mockPokemon3
        every { mockJsonPokemon4.toDomain() } returns mockPokemon4
        every { mockJsonPokemon5.toDomain() } returns mockPokemon5
    }

    @AfterEach
    fun tearDown() {
        unmockkStatic(JsonPokemonListItem::toDomain)
    }

    @Test
    fun `WHEN loadMore is called THEN load from service success`() = runTest {
        coEvery {
            service.getPagedPokemon(ITEM_PER_PAGE, offset)
        } returns Result.success(mockJsonResponse1)

        val result = repository.loadMore(ITEM_PER_PAGE)
        assertEquals(result.getOrThrow(), mockDataSet1)

        repository.data.test {
            assertEquals(awaitItem(), mockDataSet1)
        }
    }

    @Test
    fun `WHEN loadMore is called THEN load from service failure`() = runTest {
        val failure = NetworkException(httpCode = 404)
        coEvery {
            service.getPagedPokemon(ITEM_PER_PAGE, offset)
        } returns Result.failure(failure)

        val result = repository.loadMore(ITEM_PER_PAGE)

        assertEquals(result.exceptionOrNull(), failure)
    }

    @Test
    fun `GIVEN loadMore has been called WHEN loadMore is called THEN load from next page`() =
        runTest {
            `WHEN loadMore is called THEN load from service success`()

            page++
            coEvery {
                service.getPagedPokemon(ITEM_PER_PAGE, offset)
            } returns Result.success(mockJsonResponse2)

            val result = repository.loadMore(ITEM_PER_PAGE)
            assertEquals(result.getOrNull(), mockDataSet2)

            repository.data.test {
                assertEquals(awaitItem(), mockDataSetUnion)
            }
        }

    companion object {
        const val ITEM_PER_PAGE = 30
    }
}
