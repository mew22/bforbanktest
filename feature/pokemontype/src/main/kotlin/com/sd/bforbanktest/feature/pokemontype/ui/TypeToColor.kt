package com.sd.bforbanktest.feature.pokemontype.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

val mapping = mapOf<String, Int>(
        "normal" to "#A8A77A".toColorInt(),
        "fire" to "#EE8130".toColorInt(),
        "water" to "#6390F0".toColorInt(),
        "electric" to "#F7D02C".toColorInt(),
        "grass" to "#7AC74C".toColorInt(),
        "ice" to "#96D9D6".toColorInt(),
        "fighting" to "#C22E28".toColorInt(),
        "poison" to "#A33EA1".toColorInt(),
        "ground" to "#E2BF65".toColorInt(),
        "flying" to "#A98FF3".toColorInt(),
        "psychic" to "#F95587".toColorInt(),
        "bug" to "#A6B91A".toColorInt(),
        "rock" to "#B6A136".toColorInt(),
        "ghost" to "#735797".toColorInt(),
        "dragon" to "#6F35FC".toColorInt(),
        "dark" to "#705746".toColorInt(),
        "steel" to "#B7B7CE".toColorInt(),
        "fairy" to "#D685AD".toColorInt(),
)

@Composable
fun String.toColor(): Color = mapping[this]?.let { Color(it) } ?: Color.Gray
