package ru.kpfu.itis.pokemonapp.data.remote.model


import com.google.gson.annotations.SerializedName

data class PokemonInfoRemote(
    @SerializedName("name")
    val name: String?,
    @SerializedName("sprites")
    val sprites: Sprites?,
    @SerializedName("stats")
    val stats: List<Stat>?
)

data class Sprites(
    @SerializedName("other")
    val other: Other?
)

data class Other(
    @SerializedName("home")
    val home: Home?
)
data class Home(
    @SerializedName("front_default")
    val picUrl: String?
)

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val stat: StatX?
)

data class StatX(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?
)
