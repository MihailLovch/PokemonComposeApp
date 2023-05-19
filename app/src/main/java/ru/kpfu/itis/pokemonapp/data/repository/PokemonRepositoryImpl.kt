package ru.kpfu.itis.pokemonapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.kpfu.itis.pokemonapp.data.mapper.PokemonRemoteMapper
import ru.kpfu.itis.pokemonapp.data.remote.network.PokemonApi
import ru.kpfu.itis.pokemonapp.domain.PokemonRepository
import ru.kpfu.itis.pokemonapp.domain.entity.PokemonInfoEntity
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val pokemonRemoteMapper: PokemonRemoteMapper,
) : PokemonRepository {
    override suspend fun getPokemonsData(amount: Long): Flow<List<PokemonInfoEntity>> = flow {
        val list = mutableListOf<PokemonInfoEntity>()
        repeat(amount.toInt()) { index ->
            list.add(pokemonRemoteMapper.mapToPokemonInfoEntity(pokemonApi.getPokemon(index.toLong() + 1)))
        }
        emit(list)
    }
}