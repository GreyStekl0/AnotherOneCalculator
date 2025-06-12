package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import com.github.greysteklo.anotherone.calculator.ui.util.logOnFailure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
                is CalculatorAction.Equally -> enterEqually()
                is CalculatorAction.Delete -> delete()
                is CalculatorAction.Parentheses -> enterParentheses()
                is CalculatorAction.Percent -> enterPercent()
            }
        }

        private fun enterNumber(number: Int) {
            val expression = state.value.expression
            val newExpression = actions.enterNumber.execute(expression, number)
            val newResult =
                actions.calculate
                    .execute(newExpression)
                    .logOnFailure { "Failed to calculate expression: $newExpression" }
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
                    .logOnFailure { "Failed to calculate expression: $newExpression" }
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
                    .logOnFailure { "Failed to calculate expression: $newExpression" }
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
                    .logOnFailure { "Failed to calculate expression: $newExpression" }
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
                    .logOnFailure { "Failed to calculate percent of $expression" }
                    .getOrDefault("Error")
            _state.update {
                it.copy(
                    expression = newResult,
                    result = newResult,
                )
            }
        }

        private fun enterEqually() {
            if (_state.value.result != "Error") {
                _state.update {
                    it.copy(
                        expression = it.result,
                        result = it.result,
                    )
                }
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

    data object Equally : CalculatorAction()

    data object Delete : CalculatorAction()

    data object Parentheses : CalculatorAction()

    data object Percent : CalculatorAction()
}
