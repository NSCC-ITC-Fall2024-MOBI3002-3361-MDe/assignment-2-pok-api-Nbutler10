package com.example.pokemonapp.retrofit

import com.example.pokemonapp.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService { // Usage of api // More specific pointing then Retrofit
    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonResponse
}
