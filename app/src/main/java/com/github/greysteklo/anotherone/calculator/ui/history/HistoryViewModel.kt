package com.github.greysteklo.anotherone.calculator.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.usecase.ClearHistoryUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
    @Inject
    constructor(
        getHistory: GetHistoryUseCase,
        private val clearHistory: ClearHistoryUseCase,
    ) : ViewModel() {
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
    }
