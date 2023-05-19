package ru.kpfu.itis.pokemonapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.kpfu.itis.pokemonapp.data.repository.PokemonRepositoryImpl
import ru.kpfu.itis.pokemonapp.domain.PokemonRepository
import ru.kpfu.itis.pokemonapp.domain.entity.Result
import ru.kpfu.itis.pokemonapp.domain.mapper.PokemonInfoEntityMapper
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepositoryImpl,
    private val pokemonInfoEntityMapper: PokemonInfoEntityMapper,
) {
    suspend operator fun invoke(amount: Long): Result<Flow<List<Pokemon>>> =
        runCatching {
            pokemonRepository.getPokemonsData(amount).map { list ->
                list.map { pokemonInfoEntityMapper.mapToPokemon(it) }
            }.flowOn(Dispatchers.IO)

        }.fold(
            onSuccess = { Result.Success(it) },
            onFailure = { Result.Error(it.cause.toString()) }
        )
}