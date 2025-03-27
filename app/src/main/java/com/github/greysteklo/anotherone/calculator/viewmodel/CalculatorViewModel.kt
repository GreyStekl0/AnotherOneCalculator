// CalculatorViewModel.kt
package com.github.greysteklo.anotherone.calculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.greysteklo.anotherone.calculator.domain.model.CalculatorState
import com.github.greysteklo.anotherone.calculator.domain.usecase.CalculateExpressionUseCase
import com.github.greysteklo.anotherone.calculator.domain.usecase.EnterNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    private val calculateExpressionUseCase = CalculateExpressionUseCase()
    private val enterNumberUseCase = EnterNumberUseCase()

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
        val newExpression = enterNumberUseCase.execute(expression, number)
        _state.update { currentState ->
            currentState.copy(expression = newExpression)
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
            val newState = calculateExpressionUseCase.execute(state.value.expression)
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
