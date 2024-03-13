package com.example.pokeapi.details

import com.example.pokeapi.common.APIService
import retrofit2.Response
import javax.inject.Inject

interface PokemonDetailsRepositoryInterface {
    suspend fun getPokemonDetails(name: String): Response<PokemonDetailsModel>
}

class PokemonDetailsRepository @Inject constructor(
    private val apiService: APIService
): PokemonDetailsRepositoryInterface {
    override suspend fun getPokemonDetails(name: String): Response<PokemonDetailsModel> {
        return apiService.getPokemonDetails(name)
    }
}