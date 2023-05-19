package ru.kpfu.itis.pokemonapp.presentation.ui.screen.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import ru.kpfu.itis.pokemonapp.R
import ru.kpfu.itis.pokemonapp.presentation.Screen
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import ru.kpfu.itis.pokemonapp.presentation.util.compatCapitalize
import ru.kpfu.itis.pokemonapp.presentation.util.debugPlaceholder
import java.util.Locale

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    ListScreenAction(
        viewAction = action,
        eventHandler = viewModel::event,
        navController = navController
    )
    if (state.loading){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }else {
        PokemonList(
            pokemonList = state.pokemonList,
            eventHandler = viewModel::event
        )
    }
}

@Composable
private fun ListScreenAction(
    viewAction: ListAction?,
    eventHandler: (ListEvent) -> Unit,
    navController: NavController
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is ListAction.Navigate -> {
                Log.d("TEST-TAG","NAVIGATE TO ${viewAction.pokemon.name}")
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "pokemon", value = viewAction.pokemon
                )
                navController.navigate(Screen.PokemonInfo.route)
                eventHandler.invoke(ListEvent.Navigated)
            }
        }
    }
}

@Composable
private fun PokemonList(
    pokemonList: List<Pokemon>,
    eventHandler: (ListEvent) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.x2)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.x2)),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.x2))
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(pokemon, eventHandler)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonItem(pokemon: Pokemon, eventHandler: (ListEvent) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = dimensionResource(id = R.dimen.x2)),
        onClick = {
            eventHandler.invoke(ListEvent.OnItemClick(pokemon))
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.picUrl,
                contentDescription = null,
                placeholder = debugPlaceholder(debugPreview = R.drawable.ic_launcher_background)
            )
            Text(text = pokemon.name.compatCapitalize())
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun PokemonListPreview() {
    PokemonListScreen(rememberNavController())
}