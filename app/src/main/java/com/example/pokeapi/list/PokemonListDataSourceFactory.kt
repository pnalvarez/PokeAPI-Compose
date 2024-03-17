package com.example.pokeapi.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class PokemonListDataSourceFactory @Inject constructor(
    private val repository: PokemonListRepositoryInterface
) {
    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
    fun getPokemon() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PokemonListPagingSource(repository)}
    ).flow
}