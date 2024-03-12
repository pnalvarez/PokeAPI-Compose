package com.example.pokeapi

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonListItem(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
): Parcelable