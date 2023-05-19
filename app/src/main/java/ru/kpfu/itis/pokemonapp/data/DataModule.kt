package ru.kpfu.itis.pokemonapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.kpfu.itis.pokemonapp.data.remote.RemoteModule
import ru.kpfu.itis.pokemonapp.data.repository.PokemonRepositoryImpl
import ru.kpfu.itis.pokemonapp.domain.PokemonRepository

@Module(
    includes = [
        RemoteModule::class,

    ]
)
@InstallIn(ActivityComponent::class)
object DataModule {
    @Provides
    fun providePokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository =
        pokemonRepositoryImpl
}