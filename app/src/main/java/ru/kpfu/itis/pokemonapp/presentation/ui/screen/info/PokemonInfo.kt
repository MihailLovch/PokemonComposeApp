package ru.kpfu.itis.pokemonapp.presentation.ui.screen.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.kpfu.itis.pokemonapp.R
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import ru.kpfu.itis.pokemonapp.presentation.util.compatCapitalize
import ru.kpfu.itis.pokemonapp.presentation.util.debugPlaceholder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonInfoScreen(
    pokemon: Pokemon
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = pokemon.name.compatCapitalize())
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = pokemon.picUrl,
                contentDescription = null,
                placeholder = debugPlaceholder(debugPreview = R.drawable.ic_launcher_background),
                modifier = Modifier.size(240.dp)
            )

            Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.x6)))

            Row {
                IconTextItem(iconId = R.drawable.hp, text = pokemon.hp.toString())
                IconTextItem(iconId = R.drawable.attack, text = pokemon.attack.toString())
                IconTextItem(iconId = R.drawable.defense, text = pokemon.defense.toString())
            }
        }
    }
}

@Composable
private fun IconTextItem(
    @DrawableRes iconId: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = modifier.size(
                dimensionResource(id = R.dimen.iconSize)
            )
        )
        Text(text = text)
    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun PokemonInfoScreenPreview() {
    PokemonInfoScreen(
        Pokemon(
            name = "Bulbazavr",
            picUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png",
            attack = 12,
            defense = 13,
            hp = 115
        )
    )
}