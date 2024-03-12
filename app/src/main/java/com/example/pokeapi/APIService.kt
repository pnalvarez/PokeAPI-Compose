package com.example.pokeapi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonListModel>

    @GET("pokemon/{name}")
    suspend fun getPokemonSprites(
        @Path("name") name: String
    ): Response<PokemonSpritesModel>
}