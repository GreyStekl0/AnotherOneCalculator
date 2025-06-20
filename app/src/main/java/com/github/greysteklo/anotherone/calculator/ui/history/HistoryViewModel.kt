package com.github.greysteklo.anotherone.calculator.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.model.Joke
import com.github.greysteklo.anotherone.calculator.domain.usecase.ClearHistoryUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.GetHistoryUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.GetRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(
        getHistory: GetHistoryUseCase,
        private val clearHistory: ClearHistoryUseCase,
        private val getRandomJoke: GetRandomJokeUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(JokeState())
        val uiState = _uiState.asStateFlow()

        init {
            getJoke()
        }

        val history =
            getHistory()
                .map { historyList ->
                    historyList.groupBy { it.date }
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = emptyMap(),
                )

        fun onClearHistory() {
            viewModelScope.launch {
                clearHistory()
            }
        }

        fun getJoke() {
            viewModelScope.launch {
                val preloadedJoke = _uiState.value.preloadedJoke
                _uiState.update { it.copy(joke = preloadedJoke) }
                getRandomJoke()
                    .onSuccess { joke ->
                        _uiState.update { it.copy(preloadedJoke = joke) }
                    }.onFailure { _uiState.update { it.copy(joke = Joke("No joke found")) } }
            }
        }
    }

data class JokeState(
    val joke: Joke = Joke("No joke found"),
    val preloadedJoke: Joke = Joke("No joke found"),
)
