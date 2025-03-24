package com.github.greysteklo.anotherone.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.model.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel : ViewModel() {
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
        _state.update { currentState ->
            currentState.copy(
                expression = if (expression == "0") number.toString() else expression + number.toString(),
            )
        }
    }

    private fun enterOperation(operation: String) {
        _state.update { currentState ->
            currentState.copy(expression = currentState.expression + operation)
        }
    }

    private fun enterDecimal() {
        _state.update { currentState ->
            currentState.copy(expression = currentState.expression + ",")
        }
    }

    private fun clearAll() {
        _state.update {
            CalculatorState()
        }
    }

    private fun delete() {
        val expression = state.value.expression
        _state.update { currentState ->
            currentState.copy(
                expression = if (expression.length <= 1) "0" else expression.dropLast(1),
            )
        }
    }

    private fun enterParentheses() {
        // TODO: Implement parentheses logic
        // This is a placeholder for future implementation
    }

    private fun enterPercent() {
        // TODO: Implement percent logic
        // This is a placeholder for future implementation
    }

    private fun calculate() {
        viewModelScope.launch {
            try {
                val expression = state.value.expression.replace(",", ".")
                val expressionBuilder = ExpressionBuilder(expression)
                val result = expressionBuilder.build().evaluate()

                // Format result to avoid unnecessary decimal places
                val formattedResult =
                    if (result == result.toLong().toDouble()) {
                        result.toLong().toString()
                    } else {
                        result.toString().replace(".", ",")
                    }

                _state.update { currentState ->
                    currentState.copy(result = formattedResult)
                }
            } catch (_: Exception) {
                _state.update { currentState ->
                    currentState.copy(result = "Error")
                }
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

    data object Calculate : CalculatorAction()

    data object Delete : CalculatorAction()

    data object Parentheses : CalculatorAction()

    data object Percent : CalculatorAction()
}
