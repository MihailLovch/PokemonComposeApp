package ru.kpfu.itis.pokemonapp.data.mapper

import ru.kpfu.itis.pokemonapp.data.remote.model.PokemonInfoRemote
import ru.kpfu.itis.pokemonapp.domain.entity.PokemonInfoEntity
import javax.inject.Inject

class PokemonRemoteMapper @Inject constructor() {
    fun mapToPokemonInfoEntity(pokemon: PokemonInfoRemote?): PokemonInfoEntity =
        pokemon?.let { pokemonInfo ->
            PokemonInfoEntity(
                name = pokemonInfo.name ?: "",
                picUrl = pokemonInfo.sprites?.other?.home?.picUrl ?: "",
                hp = pokemonInfo.stats?.find { it.stat?.name == HP }?.baseStat ?: 0,
                attack = pokemonInfo.stats?.find { it.stat?.name == ATTACK }?.baseStat ?: 0,
                defense = pokemonInfo.stats?.find { it.stat?.name == DEFENSE }?.baseStat ?: 0,
            )
        } ?: PokemonInfoEntity(
            name = "",
            picUrl = "",
            hp = 0,
            attack = 0,
            defense = 0,
        )


    companion object {
        const val HP = "hp"
        const val DEFENSE = "defense"
        const val ATTACK = "attack"
    }
}