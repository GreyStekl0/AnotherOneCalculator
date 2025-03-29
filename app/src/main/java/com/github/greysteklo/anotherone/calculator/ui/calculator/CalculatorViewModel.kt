package com.github.greysteklo.anotherone.calculator.ui.calculator

import androidx.lifecycle.ViewModel
import com.github.greysteklo.anotherone.calculator.domain.valueobject.CalculatorState
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
            val newState = actions.calculate.execute(newExpression)
            _state.update { newState }
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
            val newState = actions.clearAll.execute()
            _state.update { newState }
        }

        private fun delete() {
            val expression = state.value.expression
            val newExpression = actions.deleteLastChar.execute(expression)
            val newState = actions.calculate.execute(newExpression)
            _state.update { newState }
        }

        private fun enterParentheses() {
            val expression = state.value.expression
            val newExpression = actions.enterParentheses.execute(expression)
            val newState = actions.calculate.execute(newExpression)
            _state.update { newState }
        }

        private fun enterPercent() {
            val expression = state.value.expression
            val newState = actions.enterPercent.execute(expression)
            _state.update { newState }
        }

        private fun enterEqually() {
            val newState = actions.enterEqually.execute(state.value)
            _state.update { newState }
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
