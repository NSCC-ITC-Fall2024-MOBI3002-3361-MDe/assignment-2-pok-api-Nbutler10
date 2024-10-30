package com.example.pokemonapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.pokemonapp.retrofit.RetrofitInstance
import com.example.pokemonapp.model.PokemonResponse

class PokemonViewModel : ViewModel() {
    private val _pokemonInfo = MutableStateFlow<PokemonResponse?>(null)
    val pokemonInfo: StateFlow<PokemonResponse?> = _pokemonInfo // Pulling from model PokemonResponse

    fun fetchPokemon(name: String) { // Get api data
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemon(name)
                _pokemonInfo.value = response
            } catch (e: Exception) {
                Log.e("PokemonViewModel", "Error fetching Pokémon: ${e.message}")
            }
        }
    }
}
