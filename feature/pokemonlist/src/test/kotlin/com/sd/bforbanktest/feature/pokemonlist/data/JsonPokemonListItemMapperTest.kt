package com.sd.bforbanktest.feature.pokemonlist.data

import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JsonPokemonListItemMapperTest {

    private val mockPokemon1: PokemonListItem = PokemonListItem("pika")
    private val mockJsonPokemon1: JsonPokemonListItem = JsonPokemonListItem("pika")
    private val mockPokemon2: PokemonListItem = PokemonListItem("cara")
    private val mockJsonPokemon2: JsonPokemonListItem = JsonPokemonListItem("cara")
    private val mockPokemon3: PokemonListItem = PokemonListItem("bulbi")
    private val mockJsonPokemon3: JsonPokemonListItem = JsonPokemonListItem("bulbi")

    private val mockJsonDataSet: List<JsonPokemonListItem> = listOf(
        mockJsonPokemon1,
        mockJsonPokemon2,
        mockJsonPokemon3,
    )

    private val mockDataSet: Set<PokemonListItem> = persistentSetOf(
        mockPokemon1,
        mockPokemon2,
        mockPokemon3,
    )

    @Test
    fun `WHEN toDomain is called THEN map json to domain entity`() = runTest {
        assertEquals(mockDataSet, mockJsonDataSet.toDomain())
    }
}
