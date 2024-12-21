package com.sd.bforbanktest.feature.pokemondetail.data

import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals

class JsonPokemonMapperTest {

    private val pokemonName = "mew"
    private val pokemonId = 151
    private val pokemonImage =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/" + "sprites/pokemon/other/official-artwork/151.png"
    private val pokemon = PokemonDetail(
        name = pokemonName,
        numberId = pokemonId,
        image = URL(pokemonImage)
    )
    private val jsonPokemon = JsonPokemon(
        name = pokemonName,
        id = pokemonId,
        sprites = JsonPokemonSprites(
            other = JsonPokemonSpritesOther(
                officialArtwork = JsonPokemonSpritesOtherOfficialArtwork(
                    frontDefault = pokemonImage
                )
            )
        )
    )

    @Test
    fun `WHEN toDomain is called THEN map json to domain entity`() = runTest {
        assertEquals(pokemon, jsonPokemon.toDomain())
    }
}
