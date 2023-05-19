package ru.kpfu.itis.pokemonapp.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val picUrl: String,
    val defense: Int,
    val attack: Int,
    val hp : Int
): Parcelable