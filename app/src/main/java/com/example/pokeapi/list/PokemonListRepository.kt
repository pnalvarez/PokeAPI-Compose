package com.example.pokeapi.list

import android.util.Log
import com.example.pokeapi.common.APIService
import retrofit2.Response
import javax.inject.Inject

interface PokemonListRepositoryInterface {
    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel>
}

class PokemonListRepository @Inject constructor(
    private val apiService: APIService
): PokemonListRepositoryInterface {
    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListModel> {
        Log.d("Repository getPokemonList", "$offset, $limit")
        return apiService.getPokemonList(offset = offset, limit = limit)
    }
}