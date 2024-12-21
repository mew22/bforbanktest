package com.sd.bforbanktest.feature.pokemondetail.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sd.bforbank.core.ui.component.DefaultBackAction
import com.sd.bforbank.core.ui.component.ErrorScreen
import com.sd.bforbank.core.ui.component.ErrorScreenDefaultActions
import com.sd.bforbank.core.ui.component.NavBar
import com.sd.bforbank.core.ui.component.annotatedStringResource
import com.sd.bforbank.core.ui.component.card.CardFlat
import com.sd.bforbanktest.feature.pokemondetail.R
import com.sd.bforbanktest.feature.pokemondetail.domain.PokemonDetail
import java.net.URL

@Composable
fun PokemonDetailRoute(
    state: PokemonDetailState,
    dispatch: (PokemonDetailEvent) -> Unit,
    popUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NavBar(
                title = state.name,
                navigationAction = DefaultBackAction(
                    onClick = popUp,
                )
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Crossfade(targetState = state.status) { status ->
            when (status) {
                is PokemonDetailState.Status.Loading -> {
                    CircularProgressIndicator()
                }

                is PokemonDetailState.Status.Error -> {
                    ErrorScreen(
                        crossIconAction = { popUp() },
                        actions = {
                            ErrorScreenDefaultActions(
                                onPrimaryActionClick = {
                                    dispatch(PokemonDetailEvent.ErrorRetryButtonClicked)
                                },
                                onSecondaryActionClick = {
                                    popUp()
                                }
                            )
                        }
                    )
                }

                is PokemonDetailState.Status.Success -> {
                    PokemonDetailScreen(
                        pokemon = status.pokemon,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun PokemonDetailScreen(
    pokemon: PokemonDetail,
    modifier: Modifier = Modifier,
) {
    CardFlat(modifier = modifier) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.image.toString())
                    .crossfade(true)
                    .build(),
                contentDescription = "pokemon",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(annotatedStringResource(R.string.pokemonId, pokemon.numberId))
            Text(annotatedStringResource(R.string.pokemonName, pokemon.name))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListPreview() {
    PokemonDetailScreen(
        PokemonDetail(
            name = "pika",
            image = URL(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + "other/official-artwork/151.png"
            ),
            numberId = 151,
        )
    )
}
