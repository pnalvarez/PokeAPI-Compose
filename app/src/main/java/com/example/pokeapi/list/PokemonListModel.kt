package com.example.pokeapi.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListModel(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItem>
) : Parcelable