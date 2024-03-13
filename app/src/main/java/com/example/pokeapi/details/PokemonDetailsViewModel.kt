package com.example.pokeapi.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonDetailsRepositoryInterface
): ViewModel() {
    private val _pokemonDetails = MutableStateFlow<PokemonDetailsModel?>(null)
    private val _isLoading = MutableStateFlow<Boolean>(true)
    private val _gotError = MutableStateFlow<Boolean>(false)
    val pokemonDetails: StateFlow<PokemonDetailsModel?> get() = _pokemonDetails.asStateFlow()
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()
    val gotError: StateFlow<Boolean> get() = _gotError.asStateFlow()

    fun fetchDetails(name: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getPokemonDetails(name)
            val error = result.errorBody()
            val data = result.body()
            if (error != null || !result.isSuccessful) {
                Log.d("Got an error", "Got an error")
                _isLoading.value = false
                _gotError.value = true
                return@launch
            }
            if (data != null) {
                Log.d("Got data", "Got data")
                _isLoading.value = false
                _pokemonDetails.value = data
            } else {
                Log.d("Got nothing", "Got data")
                _isLoading.value = false
            }
        }
    }
}