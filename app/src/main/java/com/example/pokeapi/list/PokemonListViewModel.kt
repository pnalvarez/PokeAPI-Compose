package com.example.pokeapi.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonListRepositoryInterface
): ViewModel() {
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow<Boolean>(true)

    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    private val dataSourceFactory = PokemonListDataSourceFactory(repository)
    private val _pagingData: MutableStateFlow<PagingData<PokemonListItem>> = MutableStateFlow(PagingData.empty())
    val pagingData: StateFlow<PagingData<PokemonListItem>> get() = _pagingData.asStateFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            getPokemon()
        }
    }

    private suspend fun getPokemon() {
        dataSourceFactory
            .getPokemon()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _pagingData.value = it
            }
    }
}