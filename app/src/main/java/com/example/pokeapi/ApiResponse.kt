package com.example.pokeapi

sealed class ApiResponse {
    data class Success(val data: List<PokemonListItem>): ApiResponse()
    data class Failure(val errorMsg: String?): ApiResponse()
    data object Loading : ApiResponse()
}