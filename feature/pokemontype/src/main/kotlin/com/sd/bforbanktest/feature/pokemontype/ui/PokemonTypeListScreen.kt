package com.sd.bforbanktest.feature.pokemontype.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.bforbanktest.feature.pokemontype.domain.PokemonTypeListItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PokemonTypeListRoute(
    state: PokemonTypeListState,
    navigateToPokemonList: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Crossfade(
        targetState = state,
        modifier = modifier,
    ) { status ->
        when (status) {
            is PokemonTypeListState.Idle -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            is PokemonTypeListState.Error -> {
                Text("Error received")
            }

            is PokemonTypeListState.Success -> {
                PokemonTypeListScreen(
                    list = status.list,
                    navigateToPokemonList = navigateToPokemonList,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeListScreen(
    list: ImmutableList<PokemonTypeListItem>,
    navigateToPokemonList: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokemon Type List") })
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->

        FlowRow(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            list.forEach { type ->
                PokemonTypeCard(
                    type = type,
                    onClick = { navigateToPokemonList(type.name) },
                )
            }
        }
    }
}

@Composable
fun PokemonTypeCard(
    type: PokemonTypeListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilterChip(
        selected = false,
        onClick = onClick,
        label = {
            Text(type.name)
        },
        colors = FilterChipDefaults.filterChipColors()
            .copy(containerColor = type.name.toColor()),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PokemonListPreview() {
    PokemonTypeListScreen(
        list = persistentListOf(
            PokemonTypeListItem(name = "ground"),
            PokemonTypeListItem(name = "poison"),
        ),
        {},
    )
}
