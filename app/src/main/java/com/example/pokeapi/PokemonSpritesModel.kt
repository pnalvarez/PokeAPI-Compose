package com.example.pokeapi

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpritesModel(
    @SerializedName("sprites") val sprite: SpriteModel
): Parcelable

@Parcelize
data class SpriteModel(
    @SerializedName("front_default") val image: String
): Parcelable