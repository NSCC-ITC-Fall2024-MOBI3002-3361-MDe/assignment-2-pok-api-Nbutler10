package com.example.pokemonapp.model

data class PokemonResponse( // Schema of data from api
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<Type>,
    val abilities: List<Ability>,
    val sprites: Sprites,
    val base_experience: Int
)

data class Type(val type: TypeDetail)
data class TypeDetail(val name: String)

data class Ability(val ability: AbilityDetail)
data class AbilityDetail(val name: String)

data class Sprites(
    val front_default: String
)
