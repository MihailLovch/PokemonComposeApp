package ru.kpfu.itis.pokemonapp.data.remote.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.kpfu.itis.pokemonapp.data.remote.model.PokemonInfoRemote

interface PokemonApi {
    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") pokemonId: Long
    ): PokemonInfoRemote
}