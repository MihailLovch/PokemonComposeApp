package ru.kpfu.itis.pokemonapp.domain.mapper

import ru.kpfu.itis.pokemonapp.domain.entity.PokemonInfoEntity
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import javax.inject.Inject

class PokemonInfoEntityMapper @Inject constructor() {
    fun mapToPokemon(pokemonInfoEntity: PokemonInfoEntity): Pokemon =
        Pokemon(
            name = pokemonInfoEntity.name,
            picUrl = pokemonInfoEntity.picUrl,
            defense = pokemonInfoEntity.defense,
            hp = pokemonInfoEntity.hp,
            attack = pokemonInfoEntity.attack
        )
}