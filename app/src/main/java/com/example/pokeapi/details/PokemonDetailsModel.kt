package com.example.pokeapi.details

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetailsModel(
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("types") val types: List<PokemonDetailsTypeItemModel>,
    @SerializedName("sprites") val sprite: PokemonDetailsSpritesModel
): Parcelable

@Parcelize
data class PokemonDetailsTypeModel(
    @SerializedName("name") val name: String
): Parcelable

@Parcelize
data class PokemonDetailsTypeItemModel(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: PokemonDetailsTypeModel
): Parcelable

@Parcelize
data class PokemonDetailsSpritesModel(
    @SerializedName("front_default") val imageURL: String
): Parcelable