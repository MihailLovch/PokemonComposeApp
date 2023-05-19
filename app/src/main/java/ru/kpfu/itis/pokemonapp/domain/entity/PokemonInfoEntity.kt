package ru.kpfu.itis.pokemonapp.domain.entity

data class PokemonInfoEntity(
    val name: String,
    val picUrl: String,
    val hp : Int,
    val attack: Int,
    val defense: Int,
)
