package com.sd.bforbanktest.feature.pokemonlist.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sd.bforbanktest.feature.pokemonlist.domain.PokemonListItem

@Composable
fun PokemonListRoute(
    state: PokemonListState,
    dispatch: (PokemonListEvent) -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Crossfade(
        targetState = state.status,
        modifier = modifier,
    ) { status ->
        when (status) {
            is PokemonListState.Status.Idle -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }
            }

            is PokemonListState.Status.Error -> {
                Text("Error received")
            }

            is PokemonListState.Status.Success -> {
                PokemonListScreen(
                    list = status.list,
                    isLoading = state.isLoading,
                    dispatch = dispatch,
                    navigateToDetail = navigateToDetail,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    list: MutableList<PokemonListItem>,
    isLoading: Boolean,
    dispatch: (PokemonListEvent) -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyListState()
    val shouldLoadMore by state.shouldLoadMore(isLoading = isLoading)

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            dispatch(PokemonListEvent.ListReachBottom)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokemon List") })
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->

        // Scroll state lost after data updated, see https://issuetracker.google.com/issues/214253526
        LazyColumn(
            state = state,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.Start,
            contentPadding = PaddingValues(20.dp)
        ) {
            itemsIndexed(list, key = { _, item -> item.name }) { _, pokemon ->
                PokemonListCard(
                    pokemon = pokemon,
                    modifier = Modifier.clickable { navigateToDetail(pokemon.name) }
                )
            }

            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListCard(
    pokemon: PokemonListItem,
    modifier: Modifier = Modifier,
) {
    val borderStroke = 2.dp
    val elevation = 5.dp
    val borderColor = MaterialTheme.colorScheme.primary

    Surface(
        modifier = modifier
            .shadow(elevation)
            .border(borderStroke, borderColor),
        tonalElevation = 4.dp,
    ) {
        Text(text = pokemon.name)
    }
}

@Composable
fun LazyListState.shouldLoadMore(offset: Int = 3, isLoading: Boolean = false) = remember(this) {
    derivedStateOf {
        val totalItemsCount = layoutInfo.totalItemsCount
        val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        lastVisibleItemIndex >= totalItemsCount - offset && !isLoading
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonListPreview() {
    PokemonListScreen(mutableListOf(), false, {}, {})
}
