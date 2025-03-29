package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel
    @Inject
    constructor(
        private val actions: CalculatorActions,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CalculatorState())
        val state: StateFlow<CalculatorState> = _state.asStateFlow()

        fun onAction(action: CalculatorAction) {
            when (action) {
                is CalculatorAction.Number -> enterNumber(action.number)
                is CalculatorAction.Decimal -> enterDecimal()
                is CalculatorAction.Clear -> clearAll()
                is CalculatorAction.Operation -> enterOperation(action.operation)
                is CalculatorAction.Calculate -> calculate()
                is CalculatorAction.Delete -> delete()
                is CalculatorAction.Parentheses -> enterParentheses()
                is CalculatorAction.Percent -> enterPercent()
            }
        }

        private fun enterNumber(number: Int) {
            val expression = state.value.expression
            val newExpression = actions.enterNumber.execute(expression, number)
            _state.update { currentState ->
                currentState.copy(expression = newExpression)
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
            _state.update { currentState ->
                currentState.copy(expression = actions.enterDecimal.execute(currentState.expression))
            }
        }

        private fun clearAll() {
            _state.update {
                actions.clearAll.execute()
            }
        }

        private fun delete() {
            _state.update { currentState ->
                currentState.copy(
                    expression = actions.deleteLastChar.execute(currentState.expression),
                )
            }
        }

        private fun enterParentheses() {
            // TODO: Implement parentheses logic
            // This is a placeholder for future implementation
        }

        private fun enterPercent() {
            viewModelScope.launch {
                val newState = actions.enterPercent.execute(state.value.expression)
                _state.update { newState }
            }
        }

        private fun calculate() {
            viewModelScope.launch {
                val newState = actions.calculate.execute(state.value.expression)
                _state.update { newState }
            }
        }
    }

sealed class CalculatorAction {
    data class Number(
        val number: Int,
    ) : CalculatorAction()

    data class Operation(
        val operation: String,
    ) : CalculatorAction()

    data object Decimal : CalculatorAction()

    data object Clear : CalculatorAction()

    data object Calculate : CalculatorAction()

    data object Delete : CalculatorAction()

    data object Parentheses : CalculatorAction()

    data object Percent : CalculatorAction()
}
