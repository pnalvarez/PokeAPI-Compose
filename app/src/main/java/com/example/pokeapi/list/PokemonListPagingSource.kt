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
    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(PokemonListDataSourceFactory.NETWORK_PAGE_SIZE)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(PokemonListDataSourceFactory.NETWORK_PAGE_SIZE)
        }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, PokemonListItem> = try {
        val position = params.key ?: 1
        val pageSize = params.loadSize
        val response = repository.getPokemonList(position, pageSize)
        Log.d("Pagination event", "Offset = $position Limit = ${pageSize}")
        val error = response.errorBody()
        val data = response.body()

        if(error != null) {
            LoadResult.Error(throw Exception("No Response"))
        } else if(data != null) {
            Log.d("Results", "${data.results.size}")
            LoadResult.Page(
                data = data.results,
                prevKey = if (position < pageSize) null else (position - pageSize),
                nextKey = if (position >= 1302) null else (position + pageSize)
            )
        } else {
            LoadResult.Error(throw Exception("No Response"))
        }
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}