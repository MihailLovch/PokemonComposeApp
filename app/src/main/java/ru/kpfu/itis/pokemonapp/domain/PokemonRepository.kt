package ru.kpfu.itis.pokemonapp.domain

import kotlinx.coroutines.flow.Flow
import ru.kpfu.itis.pokemonapp.data.remote.model.PokemonInfoRemote
import ru.kpfu.itis.pokemonapp.domain.entity.PokemonInfoEntity

interface PokemonRepository {
    suspend fun getPokemonsData(amount: Long) : Flow<List<PokemonInfoEntity>>
}