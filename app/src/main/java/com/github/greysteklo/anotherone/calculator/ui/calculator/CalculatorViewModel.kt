package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.model.Calculation
import com.github.greysteklo.anotherone.calculator.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel
    @Inject
    constructor(
        private val actions: CalculatorActions,
        private val repository: HistoryRepository,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CalculatorState())
        val state: StateFlow<CalculatorState> = _state.asStateFlow()

        private val _uiEvents = MutableSharedFlow<UiEvent>()
        val uiEvents = _uiEvents.asSharedFlow()

        fun onAction(action: CalculatorAction) {
            when (action) {
                is CalculatorAction.Number -> enterNumber(action.number)
                is CalculatorAction.Decimal -> enterDecimal()
                is CalculatorAction.Clear -> clearAll()
                is CalculatorAction.Operation -> enterOperation(action.operation)
                is CalculatorAction.Equally -> enterEqually()
                is CalculatorAction.Delete -> delete()
                is CalculatorAction.Parentheses -> enterParentheses()
                is CalculatorAction.Percent -> enterPercent()
                is CalculatorAction.LoadFromHistory -> loadFromHistory(action.calculation)
            }
        }

        private fun enterNumber(number: Int) {
            val expression = state.value.expression
            val newExpression = actions.enterNumber.execute(expression, number)
            val newResult =
                actions.calculate
                    .execute(newExpression)
                    .getOrDefault("Error")

            _state.update {
                it.copy(
                    expression = newExpression,
                    result = newResult,
                )
            }
        }

        private fun enterOperation(operation: String) {
            _state.update { currentState ->
                currentState.copy(
                    expression =
                        actions.enterOperation.execute(
                            currentState.expression,
                            operation,
                        ),
                )
            }
        }

        private fun enterDecimal() {
            val expression = state.value.expression
            val newExpression = actions.enterDecimal.execute(expression)
            val newResult =
                actions.calculate
                    .execute(newExpression)
                    .getOrDefault("Error")

            _state.update {
                it.copy(
                    expression = newExpression,
                    result = newResult,
                )
            }
        }

        private fun clearAll() {
            _state.update { CalculatorState() }
        }

        private fun delete() {
            val expression = state.value.expression
            val newExpression = actions.deleteLastChar.execute(expression)
            val newResult =
                actions.calculate
                    .execute(newExpression)
                    .getOrDefault("Error")

            _state.update {
                it.copy(
                    expression = newExpression,
                    result = newResult,
                )
            }
        }

        private fun enterParentheses() {
            val expression = state.value.expression
            val newExpression = actions.enterParentheses.execute(expression)
            val newResult =
                actions.calculate
                    .execute(newExpression)
                    .getOrDefault("Error")

            _state.update {
                it.copy(
                    expression = newExpression,
                    result = newResult,
                )
            }
        }

        private fun enterPercent() {
            val expression = state.value.expression
            val newResult =
                actions.enterPercent
                    .execute(expression)
                    .getOrDefault("Error")
            _state.update {
                it.copy(
                    expression = newResult,
                    result = newResult,
                )
            }
        }

        private fun loadFromHistory(calculation: Calculation) {
            _state.update {
                it.copy(
                    expression = calculation.expression,
                    result = calculation.result,
                )
            }
            viewModelScope.launch {
                _uiEvents.emit(UiEvent.CollapseHistory)
            }
        }

        private fun enterEqually() {
            val currentState = _state.value
            val expression = currentState.expression
            val result = currentState.result

            if (expression == result) return

            viewModelScope.launch {
                try {
                    repository.saveCalculation(
                        Calculation(
                            expression = expression,
                            result = result,
                        ),
                    )
                } catch (e: Exception) {
                    Timber.e(e, "Error saving $currentState to history")
                }
            }
            if (result != "Error") {
                _state.update {
                    it.copy(
                        expression = result,
                        result = result,
                    )
                }
            }
        }
    }

sealed class UiEvent {
    object CollapseHistory : UiEvent()
}

sealed class CalculatorAction {
    data class Number(
        val number: Int,
    ) : CalculatorAction()

    data class Operation(
        val operation: String,
    ) : CalculatorAction()

    data class LoadFromHistory(
        val calculation: Calculation,
    ) : CalculatorAction()

    data object Decimal : CalculatorAction()

    data object Clear : CalculatorAction()

    data object Equally : CalculatorAction()

    data object Delete : CalculatorAction()

    data object Parentheses : CalculatorAction()

    data object Percent : CalculatorAction()
}
