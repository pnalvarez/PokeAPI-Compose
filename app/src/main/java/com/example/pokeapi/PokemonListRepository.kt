package com.example.pokeapi

import android.util.Log
import retrofit2.Response
import javax.inject.Inject

interface PokemonListRepositoryInterface {
    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel>
    suspend fun getPokemonSprite(name: String): Response<PokemonSpritesModel>
}

class PokemonListRepository @Inject constructor(
    private val apiService: APIService
): PokemonListRepositoryInterface {
    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel> {
        Log.d("Repository getPokemonList", "$offset, $limit")
        return apiService.getPokemonList(offset = offset, limit = limit)
    }

    override suspend fun getPokemonSprite(name: String): Response<PokemonSpritesModel> {
        return apiService.getPokemonSprites(name = name)
    }
}