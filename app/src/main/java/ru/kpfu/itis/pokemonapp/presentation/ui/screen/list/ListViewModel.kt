package ru.kpfu.itis.pokemonapp.presentation.ui.screen.list

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.kpfu.itis.pokemonapp.domain.entity.Result
import ru.kpfu.itis.pokemonapp.domain.usecase.GetPokemonsUseCase
import ru.kpfu.itis.pokemonapp.presentation.model.Pokemon
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState> = _state.asStateFlow()

    private val _action = MutableSharedFlow<ListAction?>()
    val action: SharedFlow<ListAction?> = _action.asSharedFlow()

    fun event(listEvent: ListEvent) {
        when (listEvent) {
            is ListEvent.OnItemClick -> onItemClick(listEvent.pokemon)
            ListEvent.Navigated -> onNavigated()
        }
    }

    private fun onNavigated() {
        Log.d("TEST-TAG","NAVIGATE EVENT")
        viewModelScope.launch {
            _action.emit(null)
        }
    }

    private fun onItemClick(pokemon: Pokemon) {
        Log.d("TEST-TAG","CLICK ACTION")
        viewModelScope.launch {
            _action.emit(
                ListAction.Navigate(pokemon)
            )
        }
    }

    init {
        viewModelScope.launch {
            loadPokemons()
        }
    }

    private suspend fun loadPokemons() {
        when (val result = getPokemonsUseCase(AMOUNT_OF_DATA)) {
            is Result.Success -> {
                _state.emit(
                    _state.value.copy(
                        pokemonList = result.data.first().toPersistentList(),
                        loading = false
                    )
                )
            }

            is Result.Error -> {
                Log.d("TEST-TAG",result.message)
            }
        }
    }

    private companion object {
        const val AMOUNT_OF_DATA: Long = 10
    }
}

@Immutable
data class ListViewState(
    val pokemonList: PersistentList<Pokemon> = persistentListOf(),
    val loading: Boolean =  true
)

sealed interface ListEvent {
    data class OnItemClick(val pokemon:Pokemon) : ListEvent
    object Navigated: ListEvent
}

sealed interface ListAction {
    data class Navigate(val pokemon: Pokemon) : ListAction
}