package com.example.pokeapi.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PokemonListDataSourceFactoryInterface {
    fun getPokemon(): Flow<PagingData<PokemonListItem>>
}

class PokemonListDataSourceFactory @Inject constructor(
    private val repository: PokemonListRepositoryInterface
): PokemonListDataSourceFactoryInterface {
    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    override fun getPokemon() = Pager(
        // Metadata about pagination
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        // Paging Source instance
        pagingSourceFactory = { PokemonListPagingSource(repository)}
    ).flow
// Returns a pagination flow to be listened by the UI(like an observable)
}