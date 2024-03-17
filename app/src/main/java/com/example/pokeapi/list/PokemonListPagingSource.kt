package com.example.pokeapi.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokeapi.common.APIService
import retrofit2.Response
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
    private val repository: PokemonListRepositoryInterface
): PagingSource<Int, PokemonListItem>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? = null

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, PokemonListItem> = try {
        val offset = params.key ?: 0
        val limit = 30 // Page size

        // Call the API to fetch the Pokémon list with the calculated offset and limit
        val response = repository.getPokemonList(offset, limit)

        if (response.isSuccessful) {
            // Extract the Pokémon list from the response
            val pokemonList = response.body()?.results

            // Calculate the next page number (offset) for pagination
            val nextPage = if (pokemonList?.isEmpty() == true) {
                null // If the response is empty, there are no more pages
            } else {
                offset + limit
            }

            Log.d("Paged Results", "${pokemonList!!.size}")
            // Return the loaded data along with the next page number for pagination
            LoadResult.Page(
                data = pokemonList!!,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = nextPage
            )
        } else {
            // If the API call was not successful, return an error
            LoadResult.Error(Exception("Failed to fetch Pokémon"))
        }
    } catch (e: Exception) {
        // Handle any exceptions and return an error
        LoadResult.Error(e)
    }
}